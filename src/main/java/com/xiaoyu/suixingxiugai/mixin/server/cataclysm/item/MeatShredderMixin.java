package com.xiaoyu.suixingxiugai.mixin.server.cataclysm.item;

import com.github.L_Ender.cataclysm.items.Meat_Shredder;
import com.xiaoyu.suixingxiugai.config.cataclysm.item.MeatShredderConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Meat_Shredder.class)
public class MeatShredderMixin {

    @Redirect(
        method = "onUseTick",
        at = @At(
            value = "INVOKE",
            target = "Lcom/github/L_Ender/cataclysm/util/AttributeUtils;OriginDamage(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;)F"
        )
    )
    private float modifyMeatShredderOriginDamage(LivingEntity living, ItemStack itemStack) {
        return MeatShredderConfig.meatShredderRightClickDamage.get().floatValue() * 8.5f;
    }
}