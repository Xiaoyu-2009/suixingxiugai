package com.xiaoyu.suixingxiugai.mixin.server.bosses_of_mass_destruction.entity;

import com.cerbon.bosses_of_mass_destruction.entity.custom.gauntlet.GauntletHitboxes;
import com.xiaoyu.suixingxiugai.config.bosses_of_mass_destruction.entity.GauntletConfig;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GauntletHitboxes.class)
public class GauntletHitboxesMixin {

    @Inject(
        method = "shouldDamage(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/damagesource/DamageSource;F)Z", 
        at = @At("HEAD"), 
        cancellable = true, 
        remap = false
    )
    private void onShouldDamage(LivingEntity actor, DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (GauntletConfig.gauntletDefenseCanBeAttacked.get()) {
            cir.setReturnValue(true);
        }
    }
}