package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.events;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;

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
        if (WandConfig.enableFortificationWandInvulnerability.get()) {
            LivingEntity living = event.getEntity();
            if (!living.level().isClientSide()) {
                // 使用反射方式检查护盾，避免硬依赖
                if (hasShieldsViaReflection(living)) {
                    ci.cancel();
                }
            }
        }
    }
    
    // 使用反射方式检查是否有护盾
    private static boolean hasShieldsViaReflection(LivingEntity living) {
        try {
            // 通过反射获取CapabilityList.SHIELDS
            Class<?> capabilityListClass = Class.forName("twilightforest.capabilities.CapabilityList");
            Object shieldCapability = capabilityListClass.getField("SHIELDS").get(null);
            
            // 通过反射调用实体的getCapability方法
            Object lazyOptional = living.getClass().getMethod("getCapability", Class.forName("net.minecraftforge.common.capabilities.Capability"))
                .invoke(living, shieldCapability);
            
            // 检查LazyOptional是否存在
            boolean isPresent = (Boolean) lazyOptional.getClass().getMethod("isPresent").invoke(lazyOptional);
            if (isPresent) {
                // 获取capability实例
                Method resolveMethod = lazyOptional.getClass().getMethod("resolve");
                Object capOptional = resolveMethod.invoke(lazyOptional);
                
                if (capOptional instanceof Optional) {
                    Optional<?> optionalCap = (Optional<?>) capOptional;
                    if (optionalCap.isPresent()) {
                        Object capInstance = optionalCap.get();
                        
                        // 调用shieldsLeft方法检查是否有护盾
                        int shieldsLeft = (Integer) capInstance.getClass().getMethod("shieldsLeft").invoke(capInstance);
                        return shieldsLeft > 0;
                    }
                }
            }
        } catch (Exception e) {
            // 如果反射调用失败，返回false
            return false;
        }
        return false;
    }
}