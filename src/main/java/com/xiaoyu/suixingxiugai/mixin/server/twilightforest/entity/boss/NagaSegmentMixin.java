package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.NagaConfig;
import com.xiaoyu.suixingxiugai.util.twilightforest.entity.NagaPhysicsUtil;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import twilightforest.entity.boss.Naga;
import twilightforest.entity.boss.NagaSegment;

@Mixin(NagaSegment.class)
public class NagaSegmentMixin {
    
    @Redirect(
        method = "collideWithEntity",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
        )
    )
    private boolean redirectNagaSegmentAttackDamage(Entity instance, net.minecraft.world.damagesource.DamageSource damageSource, float originalDamage) {
        NagaSegment segment = (NagaSegment) (Object) this;
        Naga naga = segment.getParent();

        double segmentAttackDamage = NagaConfig.nagaSegmentAttackDamage.get();

        if (naga.level().getDifficulty() != net.minecraft.world.Difficulty.EASY && naga.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
            boolean hard = naga.level().getDifficulty() == net.minecraft.world.Difficulty.HARD;
            double difficultyBoost = hard ? NagaConfig.nagaDifficultySegmentAttackDamageBoostHard.get() : NagaConfig.nagaDifficultySegmentAttackDamageBoostNormal.get();
            segmentAttackDamage += difficultyBoost;
        }

        if (instance instanceof Animal) {
            segmentAttackDamage *= NagaConfig.nagaSegmentAttackDamageMultiplierAgainstAnimals.get();
        }

        return instance.hurt(damageSource, (float) segmentAttackDamage);
    }
    
    @Redirect(
        method = "hurt",
        at = @At(
            value = "INVOKE",
            target = "Ltwilightforest/entity/boss/Naga;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
        )
    )
    private boolean redirectNagaHurtCall(Naga instance, DamageSource src, float damage) {
        float damageTransferRatio = (float) NagaConfig.nagaSegmentDamageTransferRatio.get().doubleValue();
        return instance.hurt(src, damage * damageTransferRatio);
    }
    
    @Redirect(
        method = "collideWithEntity",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;push(Lnet/minecraft/world/entity/Entity;)V"
        )
    )
    private void redirectNagaSegmentPush(Entity instance, Entity entity) {
        NagaSegment segment = (NagaSegment) (Object) this;
        Naga naga = segment.getParent();
        
        NagaPhysicsUtil.applySegmentPushAndRecoilForces(naga, instance);
    }
}