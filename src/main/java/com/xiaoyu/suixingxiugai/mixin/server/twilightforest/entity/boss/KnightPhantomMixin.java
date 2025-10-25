package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.KnightPhantomConfig;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import twilightforest.entity.boss.KnightPhantom;

@Mixin(KnightPhantom.class)
public abstract class KnightPhantomMixin {
    
    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyAttributes(CallbackInfo ci) {
        KnightPhantom knightPhantom = (KnightPhantom) (Object) this;

        AttributeInstance maxHealthAttribute = knightPhantom.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            maxHealthAttribute.setBaseValue(KnightPhantomConfig.knightPhantomMaxHealth.get());
            if (knightPhantom.getHealth() > KnightPhantomConfig.knightPhantomMaxHealth.get()) {
                knightPhantom.setHealth((float) KnightPhantomConfig.knightPhantomMaxHealth.get().doubleValue());
            }
        }

        AttributeInstance attackDamageAttribute = knightPhantom.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageAttribute != null) {
            attackDamageAttribute.setBaseValue(KnightPhantomConfig.knightPhantomAttackDamage.get());
        }

        AttributeInstance armorAttribute = knightPhantom.getAttribute(Attributes.ARMOR);
        if (armorAttribute != null) {
            armorAttribute.setBaseValue(KnightPhantomConfig.knightPhantomArmor.get());
        }
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 93))
    private int modifyXpReward(int original) {
        return KnightPhantomConfig.knightPhantomXpReward.get();
    }
    
    @Inject(method = "populateDefaultEquipmentSlots", at = @At("HEAD"), cancellable = true)
    private void modifyEquipmentSlots(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        KnightPhantom knightPhantom = (KnightPhantom) (Object) this;

        String helmetItemStr = KnightPhantomConfig.knightPhantomHelmetItem.get();
        ItemStack helmetStack = getItemStackFromString(helmetItemStr);
        if (!helmetStack.isEmpty()) {
            knightPhantom.setItemSlot(EquipmentSlot.HEAD, helmetStack);
        }

        String chestplateItemStr = KnightPhantomConfig.knightPhantomChestplateItem.get();
        ItemStack chestplateStack = getItemStackFromString(chestplateItemStr);
        if (!chestplateStack.isEmpty()) {
            knightPhantom.setItemSlot(EquipmentSlot.CHEST, chestplateStack);
        }

        String leggingsItemStr = KnightPhantomConfig.knightPhantomLeggingsItem.get();
        ItemStack leggingsStack = getItemStackFromString(leggingsItemStr);
        if (!leggingsStack.isEmpty() && !leggingsStack.getItem().equals(Items.AIR)) {
            knightPhantom.setItemSlot(EquipmentSlot.LEGS, leggingsStack);
        }

        String bootsItemStr = KnightPhantomConfig.knightPhantomBootsItem.get();
        ItemStack bootsStack = getItemStackFromString(bootsItemStr);
        if (!bootsStack.isEmpty() && !bootsStack.getItem().equals(Items.AIR)) {
            knightPhantom.setItemSlot(EquipmentSlot.FEET, bootsStack);
        }

        String offHandItemStr = KnightPhantomConfig.knightPhantomOffHandItem.get();
        ItemStack offHandStack = getItemStackFromString(offHandItemStr);
        if (!offHandStack.isEmpty() && !offHandStack.getItem().equals(Items.AIR)) {
            knightPhantom.setItemSlot(EquipmentSlot.OFFHAND, offHandStack);
        }

        ci.cancel();
    }
    
    @Redirect(
        method = "setNumber", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/KnightPhantom;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", 
            ordinal = 0
        )
    )
    private void redirectSetItemSlot0(KnightPhantom knightPhantom, EquipmentSlot slot, ItemStack stack) {
        String mainHandItemStr = KnightPhantomConfig.knightPhantomMainHandNumber0Item.get();
        ItemStack mainHandStack = getItemStackFromString(mainHandItemStr);
        knightPhantom.setItemSlot(slot, mainHandStack);
    }
    
    @Redirect(
        method = "setNumber", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/KnightPhantom;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", 
            ordinal = 1
        )
    )
    private void redirectSetItemSlot1(KnightPhantom knightPhantom, EquipmentSlot slot, ItemStack stack) {
        String mainHandItemStr = KnightPhantomConfig.knightPhantomMainHandNumber1Item.get();
        ItemStack mainHandStack = getItemStackFromString(mainHandItemStr);
        knightPhantom.setItemSlot(slot, mainHandStack);
    }
    
    @Redirect(
        method = "setNumber", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/KnightPhantom;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V", 
            ordinal = 2
        )
    )
    private void redirectSetItemSlot2(KnightPhantom knightPhantom, EquipmentSlot slot, ItemStack stack) {
        String mainHandItemStr = KnightPhantomConfig.knightPhantomMainHandNumber2Item.get();
        ItemStack mainHandStack = getItemStackFromString(mainHandItemStr);
        knightPhantom.setItemSlot(slot, mainHandStack);
    }

    private ItemStack getItemStackFromString(String itemStr) {
        if (itemStr == null || itemStr.isEmpty()) {
            return ItemStack.EMPTY;
        }

        if (itemStr.equals("minecraft:air")) {
            return new ItemStack(Items.AIR);
        }
        
        try {
            ResourceLocation itemLocation = new ResourceLocation(itemStr);
            Item item = ForgeRegistries.ITEMS.getValue(itemLocation);
            if (item != null) {
                return new ItemStack(item);
            }
        } catch (Exception e) {}
        
        return ItemStack.EMPTY;
    }
}