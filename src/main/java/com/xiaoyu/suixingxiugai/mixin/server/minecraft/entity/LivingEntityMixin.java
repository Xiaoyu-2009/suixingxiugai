package com.xiaoyu.suixingxiugai.mixin.server.minecraft.entity;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;
import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.xiaoyu.suixingxiugai.util.EntityTypeHelper;
import com.xiaoyu.suixingxiugai.network.DamageDisplayMessage;
import com.xiaoyu.suixingxiugai.network.NetworkHandler;
import com.xiaoyu.suixingxiugai.event.CriticalHitEventHandler;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        if (WandConfig.enableFortificationWandInvulnerability.get()) {
            LivingEntity entity = (LivingEntity) (Object) this;

            try {
                Class<?> capabilityListClass = Class.forName("twilightforest.capabilities.CapabilityList");
                Object shieldsCapability = capabilityListClass.getDeclaredField("SHIELDS").get(null);
                
                Object lazyOptional = entity.getClass()
                    .getMethod("getCapability", Object.class)
                    .invoke(entity, shieldsCapability);
                
                lazyOptional.getClass().getMethod("ifPresent", java.util.function.Consumer.class)
                    .invoke(lazyOptional, (java.util.function.Consumer<?>) cap -> {
                        try {
                            int shieldsLeft = (int) cap.getClass().getMethod("shieldsLeft").invoke(cap);
                            if (shieldsLeft > 0) {
                                cir.cancel();
                            }
                        } catch (Exception e) {}
                    });
            } catch (Exception e) {}
        }
    }
    
    @Inject(method = "actuallyHurt", at = @At("TAIL"))
    private void onActuallyHurt(DamageSource damageSrc, float damageAmount, CallbackInfo ci) {
        if ((Object) this instanceof Player) {
            return;
        }
        
        if (!SuixingxiugaiConfig.enableDamageNumberDisplay.get()) {
            return;
        }

        LivingEntity entity = (LivingEntity) (Object) this;

        ResourceLocation entityTypeId = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        String entityTypeIdStr = entityTypeId.toString();
        
        String entityTypeConfig = SuixingxiugaiConfig.damageNumberDisplayEntityTypes.get();
        List<? extends String> entityListConfig = SuixingxiugaiConfig.damageNumberDisplayEntities.get();
        List<? extends String> blacklistEntitiesConfig = SuixingxiugaiConfig.damageNumberDisplayBlacklistEntities.get();

        boolean isBlacklisted = blacklistEntitiesConfig.contains(entityTypeIdStr);
        if (isBlacklisted) {
            return;
        }
        
        boolean matchesEntityType = EntityTypeHelper.matchesEntityType(entity, entityTypeConfig);
        boolean matchesEntityList = entityListConfig.isEmpty() || entityListConfig.contains(entityTypeIdStr);

        if (!entity.level().isClientSide && matchesEntityType && matchesEntityList && damageAmount != 0) {
            boolean isCrit = false;
            if (damageSrc.is(net.minecraft.world.damagesource.DamageTypes.PLAYER_ATTACK)) {
                isCrit = CriticalHitEventHandler.isLastAttackCritical(entity);
            }
            
            DamageDisplayMessage message = new DamageDisplayMessage(entity, damageAmount, damageSrc, isCrit);
            NetworkHandler.sendToAllTracking(entity, message);
        }
    }
}