package com.xiaoyu.suixingxiugai.mixin.server.minecraft.entity;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.FortificationWandConfig;
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

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (FortificationWandConfig.enableFortificationWandInvulnerability.get()) {
            LivingEntity entity = (LivingEntity) (Object) this;
            
            if (hasShieldsViaReflection(entity)) {
                cir.cancel();
            }
        }
    }

    private static boolean hasShieldsViaReflection(LivingEntity entity) {
        try {
            Class<?> capabilityListClass = Class.forName("twilightforest.capabilities.CapabilityList");
            Object shieldCapability = capabilityListClass.getField("SHIELDS").get(null);
            Object lazyOptional = entity.getClass().getMethod("getCapability", Class.forName("net.minecraftforge.common.capabilities.Capability")).invoke(entity, shieldCapability);
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