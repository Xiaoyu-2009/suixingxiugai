package com.xiaoyu.suixingxiugai.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.*;

public class ItemConfigHelper {

    public static boolean isItemMatchConfig(ItemStack itemStack, List<? extends String> configList) {
        if (itemStack.isEmpty()) {
            return false;
        }
        
        Item item = itemStack.getItem();
        ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(item);
        if (itemKey == null) {
            return false;
        }
        
        String itemString = itemKey.toString();
        String modId = itemKey.getNamespace();

        for (String configItem : configList) {
            if (configItem.startsWith("# ")) {
                continue;
            }

            if (configItem.contains("&&") || configItem.contains("/")) {
                if (isMultiItemContains(itemStack, itemString, modId, configItem)) {
                    return true;
                }
                continue;
            }
            
            if (isItemMatch(itemStack, itemString, modId, configItem)) {
                return true;
            }
        }
        
        return false;
    }

    private static boolean isMultiItemContains(ItemStack itemStack, String itemString, String modId, String multiItemConfig) {
        String[] requiredItems = multiItemConfig.contains("&&") ? 
        multiItemConfig.split("&&") : multiItemConfig.split("/");

        for (String item : requiredItems) {
            String trimmedItem = item.trim();
            if (isItemMatch(itemStack, itemString, modId, trimmedItem)) {
                return true;
            }
        }
        
        return false;
    }

    public static boolean isWearingArmorFromConfig(LivingEntity entity, List<? extends String> configList) {
        return isWearingItemsFromConfig(entity, configList, ItemConfigHelper::isSingleArmorEquipped, ItemConfigHelper::isWearingRequiredArmorSet);
    }

    public static boolean isWearingCuriosFromConfig(LivingEntity entity, List<? extends String> configList) {
        return isWearingItemsFromConfig(entity, configList, ItemConfigHelper::isSingleCurioEquipped, ItemConfigHelper::isWearingRequiredCuriosSet);
    }

    private static boolean isWearingItemsFromConfig(
        LivingEntity entity, 
        List<? extends String> configList,
        java.util.function.BiPredicate<LivingEntity, String> singleItemChecker,
        java.util.function.BiPredicate<LivingEntity, String> multiItemChecker
    ) {
        for (String configItem : configList) {
            if (configItem.startsWith("# ")) {
                continue;
            }

            if (configItem.contains("&&") || configItem.contains("/")) {
                if (multiItemChecker.test(entity, configItem)) {
                    return true;
                }
            } else {
                if (singleItemChecker.test(entity, configItem)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private static boolean isItemMatch(ItemStack itemStack, String itemString, String modId, String itemConfig) {
        if (itemStack.isEmpty()) {
            return false;
        }
        
        if (itemConfig.startsWith("#") && itemConfig.length() <= 1) {
            return false;
        }

        if (itemConfig.startsWith("@")) {
            String configModId = itemConfig.substring(1);
            return configModId.equals(modId);
        }
        
        if (itemConfig.startsWith("#") && itemConfig.length() > 1 && !itemConfig.startsWith("# ")) {
            String tagString = itemConfig.substring(1);

            String[] parts = tagString.split(":", 2);
            String tagNamespace;
            String tagPath;
            
            if (parts.length == 2) {
                tagNamespace = parts[0];
                tagPath = parts[1];
            } else {
                tagNamespace = "minecraft";
                tagPath = tagString;
            }

            ResourceLocation tagLocation = new ResourceLocation(tagNamespace, tagPath);
            TagKey<Item> tagKey = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), tagLocation);

            return itemStack.is(tagKey);
        }

        return itemConfig.equals(itemString);
    }

    private static boolean isSingleArmorEquipped(LivingEntity entity, String armorConfig) {
        EquipmentSlot[] slots = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        
        for (EquipmentSlot slot : slots) {
            ItemStack itemStack = entity.getItemBySlot(slot);
            if (isItemMatchForSlot(itemStack, armorConfig)) {
                return true;
            }
        }
        
        return false;
    }

    private static boolean isSingleCurioEquipped(LivingEntity entity, String curioConfig) {
        return CuriosApi.getCuriosInventory(entity)
            .map(handler -> !handler.findCurios(stack -> {
                return isItemMatchForSlot(stack, curioConfig);
            }).isEmpty())
        .orElse(false);
    }

    private static boolean isItemMatchForSlot(ItemStack itemStack, String itemConfig) {
        if (itemStack.isEmpty()) {
            return false;
        }
        
        Item item = itemStack.getItem();
        ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(item);
        if (itemKey != null) {
            String itemString = itemKey.toString();
            String modId = itemKey.getNamespace();
            return isItemMatch(itemStack, itemString, modId, itemConfig);
        }
        
        return false;
    }

    private static boolean isWearingRequiredArmorSet(LivingEntity entity, String armorSetConfig) {
        return isWearingRequiredItemSet(entity, armorSetConfig, false);
    }
    
    private static boolean isWearingRequiredCuriosSet(LivingEntity entity, String curioSetConfig) {
        return isWearingRequiredItemSet(entity, curioSetConfig, true);
    }

    private static boolean isWearingRequiredItemSet(
        LivingEntity entity, 
        String itemSetConfig,
        boolean isCurios
    ) {
        String[] requiredItems = itemSetConfig.contains("&&") ? 
        itemSetConfig.split("&&") : itemSetConfig.split("/");

        Map<String, Integer> requiredCounts = new HashMap<>();
        Map<String, Integer> foundCounts = new HashMap<>();

        for (String item : requiredItems) {
            String trimmedItem = item.trim();
            requiredCounts.put(trimmedItem, requiredCounts.getOrDefault(trimmedItem, 0) + 1);
            foundCounts.put(trimmedItem, 0);
        }

        EquipmentSlot[] slots = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        for (EquipmentSlot slot : slots) {
            ItemStack itemStack = entity.getItemBySlot(slot);
            checkItemSlot(itemStack, foundCounts, requiredCounts);
        }

        if (isCurios) {
            CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {
                Map<String, ICurioStacksHandler> curios = handler.getCurios();
                for (Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                    ICurioStacksHandler stacksHandler = entry.getValue();
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        ItemStack stack = stacksHandler.getStacks().getStackInSlot(i);
                        checkItemSlot(stack, foundCounts, requiredCounts);
                    }
                }
            });
        }

        for (Map.Entry<String, Integer> entry : requiredCounts.entrySet()) {
            String requiredItem = entry.getKey();
            int requiredCount = entry.getValue();
            int foundCount = foundCounts.getOrDefault(requiredItem, 0);
            
            if (foundCount < requiredCount) {
                return false;
            }
        }
        
        return true;
    }

    private static void checkItemSlot(ItemStack itemStack, Map<String, Integer> foundCounts, Map<String, Integer> requiredCounts) {
        if (!itemStack.isEmpty()) {
            Item item = itemStack.getItem();
            ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(item);
            if (itemKey != null) {
                String itemString = itemKey.toString();
                String modId = itemKey.getNamespace();

                for (String requiredItem : requiredCounts.keySet()) {
                    if (isItemMatch(itemStack, itemString, modId, requiredItem)) {
                        int currentCount = foundCounts.getOrDefault(requiredItem, 0);
                        foundCounts.put(requiredItem, currentCount + 1);
                    }
                }
            }
        }
    }
}