package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.events;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.WandConfig;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import twilightforest.capabilities.CapabilityList;

@Mixin(targets = "twilightforest.events.CapabilityEvents", remap = false)
public class CapabilityEventsMixin {
    
    @Inject(method = "livingAttack", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onLivingAttack(LivingAttackEvent event, CallbackInfo ci) {
        if (WandConfig.enableFortificationWandInvulnerability.get()) {
            LivingEntity living = event.getEntity();
            if (!living.level().isClientSide()) {
                living.getCapability(CapabilityList.SHIELDS).ifPresent(cap -> {
                    if (cap.shieldsLeft() > 0) {
                        ci.cancel();
                    }
                });
            }
        }
    }
}