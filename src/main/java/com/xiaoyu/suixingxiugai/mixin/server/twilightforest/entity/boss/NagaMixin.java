package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.naga.NagaConfig;
import com.xiaoyu.suixingxiugai.entity.ai.goal.TeleportToHomeGoal;
import com.xiaoyu.suixingxiugai.util.NagaPhysicsUtil;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

import twilightforest.entity.boss.Naga;

@Mixin(Naga.class)
public class NagaMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyAttributes(CallbackInfo ci) {
        Naga naga = (Naga) (Object) this;

        AttributeInstance maxHealthAttribute = naga.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            maxHealthAttribute.setBaseValue(NagaConfig.nagaMaxHealth.get());
        }

        AttributeInstance movementSpeedAttribute = naga.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeedAttribute != null) {
            movementSpeedAttribute.setBaseValue(NagaConfig.nagaMovementSpeed.get());
        }

        AttributeInstance attackDamageAttribute = naga.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageAttribute != null) {
            attackDamageAttribute.setBaseValue(NagaConfig.nagaAttackDamage.get());
        }

        AttributeInstance followRangeAttribute = naga.getAttribute(Attributes.FOLLOW_RANGE);
        if (followRangeAttribute != null) {
            followRangeAttribute.setBaseValue(NagaConfig.nagaFollowRange.get());
        }

        AttributeInstance knockbackResistanceAttribute = naga.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
        if (knockbackResistanceAttribute != null) {
            knockbackResistanceAttribute.setBaseValue(NagaConfig.nagaKnockbackResistance.get());
        }
    }

    @Redirect(
        method = "*",
        at = @At(
            value = "INVOKE",
            target = "Ltwilightforest/entity/boss/Naga;isMobWithinHomeArea(Lnet/minecraft/world/entity/Entity;)Z"
        )
    )
    private boolean redirectAllIsMobWithinHomeAreaCalls(Naga naga, Entity entity) {
        return NagaPhysicsUtil.isEntityWithinNagaCourtyard(naga, entity);
    }
    
    @Redirect(
        method = "doHurtTarget",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;push(DDD)V"
        )
    )
    private void redirectNagaRecoilPush(Entity instance, double x, double y, double z) {
        Naga naga = (Naga) (Object) this;
        if (instance == naga) {
            float yRot = naga.getYRot();
            double recoilForce = NagaConfig.nagaHeadChargeRecoilForce.get();
            double recoilX = -Mth.sin((yRot * Mth.PI) / 180.0F) * recoilForce;
            double recoilY = 0.4F * recoilForce;
            double recoilZ = Mth.cos((yRot * Mth.PI) / 180.0F) * recoilForce;
            
            naga.push(recoilX, recoilY, recoilZ);
        } else {
            NagaPhysicsUtil.applyHeadPushAndRecoilForces(naga, instance);
        }
    }

    @Redirect(
        method = "registerGoals",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V",
            ordinal = 4
        )
    )
    private void redirectAddAttemptToGoHomeGoal(GoalSelector goalSelector, int priority, net.minecraft.world.entity.ai.goal.Goal goal) {
        Naga naga = (Naga) (Object) this;
        goalSelector.addGoal(priority, new TeleportToHomeGoal<>(naga, 1.0D));
    }

    @Redirect(
        method = "customServerAiStep",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z",
            ordinal = 0
        )
    )
    private boolean redirectLeafBlockCheckInCustomServerAiStep(
        net.minecraft.world.level.block.state.BlockState state, 
        net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> tagKey
    ) {
        if (!NagaConfig.nagaCanDestroyBlocks.get()) {
            return false;
        }
        return state.is(tagKey);
    }

    @ModifyVariable(
        method = "tickDeath",
        at = @At("STORE"),
        ordinal = 0
    )
    private int modifyRenderEnd(int original) {
        return NagaConfig.nagaDeathAnimationStartDelay.get();
    }

    @ModifyVariable(
        method = "tickDeath",
        at = @At("STORE"),
        ordinal = 1
    )
    private int modifyMaxDeath(int original) {
        int startDelay = NagaConfig.nagaDeathAnimationStartDelay.get();
        int duration = NagaConfig.nagaDeathAnimationDuration.get();
        return startDelay + duration;
    }
}