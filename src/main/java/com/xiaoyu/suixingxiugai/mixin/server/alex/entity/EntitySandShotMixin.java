package com.xiaoyu.suixingxiugai.mixin.server.alex.entity;

import com.github.alexthe666.alexsmobs.entity.EntitySandShot;
import com.xiaoyu.suixingxiugai.config.alex.item.PocketSandConfig;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntitySandShot.class)
public class EntitySandShotMixin {
    
    @Redirect(
        method = "onEntityHit",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
        )
    )
    private boolean modifyPocketSandDamage(Entity target, DamageSource source, float amount) {
        float damage = PocketSandConfig.pocketSandDamage.get().floatValue();
        return target.hurt(source, damage);
    }
    
    @Redirect(
        method = "onEntityHit",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"
        )
    )
    private boolean modifyPocketSandEffect(LivingEntity target, MobEffectInstance effectInstance) {
        String effectId = PocketSandConfig.pocketSandEffectId.get();
        int effectLevel = PocketSandConfig.pocketSandEffectLevel.get();
        int effectDuration = PocketSandConfig.pocketSandEffectDuration.get();

        Registry<MobEffect> mobEffectRegistry = target.level().registryAccess().registryOrThrow(Registries.MOB_EFFECT);
        MobEffect effect = mobEffectRegistry.get(new ResourceLocation(effectId));
        if (effect != null) {
            MobEffectInstance newEffectInstance = new MobEffectInstance(effect, effectDuration, effectLevel, true, false);
            return target.addEffect(newEffectInstance);
        }

        return target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, effectDuration, effectLevel, true, false));
    }
}