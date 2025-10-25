package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.NagaConfig;
import com.xiaoyu.suixingxiugai.entity.ai.goal.TeleportToHomeGoal;
import com.xiaoyu.suixingxiugai.util.twilightforest.entity.NagaPhysicsUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import twilightforest.entity.boss.Naga;
import twilightforest.entity.ai.goal.NagaMovementPattern;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(Naga.class)
public class NagaMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyAttributes(EntityType<? extends Naga> type, Level level, CallbackInfo ci) {
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

    @ModifyConstant(method = "customServerAiStep", constant = @Constant(intValue = 15))
    private int modifyStunDamageThreshold(int original) {
        return NagaConfig.nagaStunDamageThreshold.get();
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 217))
    private int modifyXpReward(int original) {
        return NagaConfig.nagaXpReward.get();
    }

    @ModifyConstant(method = "finalizeSpawn", constant = @Constant(doubleValue = 80.0))
    private double modifyNormalDifficultyHealthBoost(double original) {
        return NagaConfig.nagaDifficultyHealthBoostNormal.get();
    }

    @ModifyConstant(method = "finalizeSpawn", constant = @Constant(doubleValue = 130.0))
    private double modifyHardDifficultyHealthBoost(double original) {
        return NagaConfig.nagaDifficultyHealthBoostHard.get();
    }

    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void addDifficultyAttackDamageBoost(ServerLevelAccessor accessor, DifficultyInstance difficulty, MobSpawnType type, SpawnGroupData data, CompoundTag tag, CallbackInfoReturnable<SpawnGroupData> cir) {
        Naga naga = (Naga) (Object) this;
        
        if (naga.level().getDifficulty() != Difficulty.EASY && naga.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
            boolean hard = naga.level().getDifficulty() == Difficulty.HARD;
            AttributeModifier modifier = new AttributeModifier("Difficulty Attack Damage Boost", hard ? NagaConfig.nagaDifficultyAttackDamageBoostHard.get() : NagaConfig.nagaDifficultyAttackDamageBoostNormal.get(), AttributeModifier.Operation.ADDITION);
            if (!Objects.requireNonNull(naga.getAttribute(Attributes.ATTACK_DAMAGE)).hasModifier(modifier)) {
                Objects.requireNonNull(naga.getAttribute(Attributes.ATTACK_DAMAGE)).addPermanentModifier(modifier);
            }
        }
    }

    @ModifyConstant(method = "customServerAiStep", constant = @Constant(intValue = 600))
    private int modifyHealingDelay(int original) {
        return NagaConfig.nagaHealingDelay.get();
    }

    @ModifyConstant(method = "doHurtTarget", constant = @Constant(intValue = 200))
    private int modifyShieldCooldown(int original) {
        return NagaConfig.nagaShieldCooldown.get();
    }

    @Redirect(
        method = "doHurtTarget",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V",
            ordinal = 0
        )
    )
    private void redirectNormalShieldDamage(ItemStack instance, int amount, LivingEntity entity, Consumer<LivingEntity> onBroken) {
        instance.hurtAndBreak(NagaConfig.nagaShieldDamageOnCharge.get(), entity, onBroken);
    }

    @Redirect(
        method = "doHurtTarget",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V",
            ordinal = 1
        )
    )
    private void redirectEnragedShieldDamage(ItemStack instance, int amount, LivingEntity entity, Consumer<LivingEntity> onBroken) {
        instance.hurtAndBreak(NagaConfig.nagaShieldDamageOnChargeEnraged.get(), entity, onBroken);
    }

    @Redirect(
        method = "doHurtTarget",
        at = @At(
            value = "INVOKE",
            target = "Ltwilightforest/entity/boss/Naga;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
            ordinal = 0
        )
    )
    private boolean redirectNagaSelfDamage(Naga naga, DamageSource source, float amount) {
        if (naga.getMovementAI() != null && naga.getMovementAI().getState() == NagaMovementPattern.MovementState.STUNLESS_CHARGE) {
            return naga.hurt(source, NagaConfig.nagaAttackKnockbackForceEnraged.get().floatValue());
        }
        return naga.hurt(source, NagaConfig.nagaAttackKnockbackForce.get().floatValue());
    }

    @Redirect(
        method = "doHurtTarget",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
        )
    )
    private boolean redirectEnragedPlayerDamage(LivingEntity instance, DamageSource source, float amount) {
        boolean result = instance.hurt(source, amount);

        if (source.getDirectEntity() instanceof Naga naga) {
            if (naga.getMovementAI() != null && naga.getMovementAI().getState() == NagaMovementPattern.MovementState.STUNLESS_CHARGE) {
                naga.hurt(naga.damageSources().generic(), NagaConfig.nagaAttackKnockbackForceEnraged.get().floatValue());
            }
        }
        
        return result;
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
    private void redirectAddAttemptToGoHomeGoal(GoalSelector goalSelector, int priority, Goal goal) {
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
    private boolean redirectLeafBlockCheckInCustomServerAiStep(BlockState state, TagKey<Block> tagKey) {
        if (!NagaConfig.nagaCanDestroyBlocks.get()) {
            return false;
        }
        return state.is(tagKey);
    }

    @Redirect(
        method = "customServerAiStep",
        at = @At(
            value = "INVOKE",
            target = "Ltwilightforest/entity/boss/Naga;shouldDestroyAllBlocks()Z",
            ordinal = 0
        )
    )
    private boolean redirectShouldDestroyAllBlocksForMinY(Naga naga) {
        if (naga.isCharging() && !NagaConfig.nagaCanDestroyBlocksOnCharge.get()) {
            return false;
        }
        return naga.shouldDestroyAllBlocks();
    }

    @Redirect(
        method = "customServerAiStep",
        at = @At(
            value = "INVOKE",
            target = "Ltwilightforest/entity/boss/Naga;shouldDestroyAllBlocks()Z",
            ordinal = 1
        )
    )
    private boolean redirectShouldDestroyAllBlocksForBlockDestruction(Naga naga) {
        if (naga.isCharging() && !NagaConfig.nagaCanDestroyBlocksOnCharge.get()) {
            return false;
        }
        return naga.shouldDestroyAllBlocks();
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