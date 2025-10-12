package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.events;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.FortificationWandConfig;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Method;
import java.util.Optional;

@Mixin(targets = "twilightforest.events.CapabilityEvents", remap = false)
public class CapabilityEventsMixin {
    
    @Inject(method = "livingAttack", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onLivingAttack(LivingAttackEvent event, CallbackInfo ci) {
        if (FortificationWandConfig.enableFortificationWandInvulnerability.get()) {
            LivingEntity living = event.getEntity();
            if (!living.level().isClientSide()) {
                if (hasShieldsViaReflection(living)) {
                    ci.cancel();
                }
            }
        }
    }

    private static boolean hasShieldsViaReflection(LivingEntity living) {
        try {
            Class<?> capabilityListClass = Class.forName("twilightforest.capabilities.CapabilityList");
            Object shieldCapability = capabilityListClass.getField("SHIELDS").get(null);
            Object lazyOptional = living.getClass().getMethod("getCapability", Class.forName("net.minecraftforge.common.capabilities.Capability")).invoke(living, shieldCapability);
            boolean isPresent = (Boolean) lazyOptional.getClass().getMethod("isPresent").invoke(lazyOptional);
            
            if (isPresent) {
                Method resolveMethod = lazyOptional.getClass().getMethod("resolve");
                Object capOptional = resolveMethod.invoke(lazyOptional);
                
                if (capOptional instanceof Optional) {
                    Optional<?> optionalCap = (Optional<?>) capOptional;
                    if (optionalCap.isPresent()) {
                        Object capInstance = optionalCap.get();

                        int shieldsLeft = (Integer) capInstance.getClass().getMethod("shieldsLeft").invoke(capInstance);
                        return shieldsLeft > 0;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}