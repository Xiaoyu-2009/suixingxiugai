package com.xiaoyu.suixingxiugai.mixin.server.cataclysm.item;

import com.github.L_Ender.cataclysm.items.Meat_Shredder;
import com.xiaoyu.suixingxiugai.config.cataclysm.item.MeatShredderConfig;

import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.ai.attributes.Attribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Meat_Shredder.class)
public class MeatShredderMixin {
    
    @Redirect(
        method = "onUseTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;getAttributeValue(Lnet/minecraft/world/entity/ai/attributes/Attribute;)D"
        )
    )
    private double modifyMeatShredderDamage(LivingEntity instance, Attribute attribute) {
        return MeatShredderConfig.meatShredderRightClickDamage.get() * 8.5;
    }
}