package com.xiaoyu.suixingxiugai.mixin.server.minecraft.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import twilightforest.capabilities.CapabilityList;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (WandConfig.enableFortificationWandInvulnerability.get()) {
            LivingEntity entity = (LivingEntity) (Object) this;

            entity.getCapability(CapabilityList.SHIELDS).ifPresent(cap -> {
                if (cap.shieldsLeft() > 0) {
                    cir.cancel();
                }
            });
        }
    }
}