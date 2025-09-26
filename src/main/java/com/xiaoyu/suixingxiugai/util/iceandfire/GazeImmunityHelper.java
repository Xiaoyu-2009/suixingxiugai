package com.xiaoyu.suixingxiugai.util.iceandfire;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.GazeImmunityConfig;
import com.xiaoyu.suixingxiugai.util.ItemConfigHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class GazeImmunityHelper {
    
    public static boolean isImmuneToGazeAttack(LivingEntity entity) {
        if (hasGazeImmunityItems()) {
            if (ItemConfigHelper.isWearingArmorFromConfig(entity, GazeImmunityConfig.gazeImmunityArmorList.get())) {
                return true;
            }
        }

        if (hasGazeImmunityInventoryItems()) {
            for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}) {
                ItemStack stack = entity.getItemBySlot(slot);
                if (!stack.isEmpty() && ItemConfigHelper.isItemMatchConfig(stack, GazeImmunityConfig.gazeImmunityItemList.get())) {
                    return true;
                }
            }

            if (entity instanceof Player player) {
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack stack = player.getInventory().getItem(i);
                    if (!stack.isEmpty() && ItemConfigHelper.isItemMatchConfig(stack, GazeImmunityConfig.gazeImmunityItemList.get())) {
                        return true;
                    }
                }
            }
        }

        if (hasGazeImmunityCuriosItems()) {
            if (ItemConfigHelper.isWearingCuriosFromConfig(entity, GazeImmunityConfig.gazeImmunityCuriosList.get())) {
                return true;
            }
        }
        
        return false;
    }

    public static boolean isGorgonBlindedByWearingImmunityItems(LivingEntity gorgon) {
        return hasGazeImmunityItems() || hasGazeImmunityInventoryItems() || hasGazeImmunityCuriosItems();
    }

    public static boolean hasGazeImmunityItems() {
        List<? extends String> immunityItems = GazeImmunityConfig.gazeImmunityArmorList.get();
        return !immunityItems.isEmpty();
    }
    
    public static boolean hasGazeImmunityInventoryItems() {
        List<? extends String> immunityItems = GazeImmunityConfig.gazeImmunityItemList.get();
        return !immunityItems.isEmpty();
    }
    
   public static boolean hasGazeImmunityCuriosItems() {
        List<? extends String> immunityItems = GazeImmunityConfig.gazeImmunityCuriosList.get();
        return !immunityItems.isEmpty();
    }
}