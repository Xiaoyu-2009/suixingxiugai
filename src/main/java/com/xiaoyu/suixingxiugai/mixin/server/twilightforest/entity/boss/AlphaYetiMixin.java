package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.AlphaYetiConfig;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Constant;

import twilightforest.entity.boss.AlphaYeti;
import twilightforest.entity.ai.goal.ThrowRiderGoal;
import twilightforest.entity.ai.goal.YetiRampageGoal;
import twilightforest.entity.ai.goal.YetiTiredGoal;

@Mixin(AlphaYeti.class)
public abstract class AlphaYetiMixin {
    
    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyAttributes(CallbackInfo ci) {
        AlphaYeti yeti = (AlphaYeti) (Object) this;

        AttributeInstance maxHealthAttribute = yeti.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            maxHealthAttribute.setBaseValue(AlphaYetiConfig.alphaYetiMaxHealth.get());
            if (yeti.getHealth() > AlphaYetiConfig.alphaYetiMaxHealth.get()) {
                yeti.setHealth((float) AlphaYetiConfig.alphaYetiMaxHealth.get().doubleValue());
            }
        }

        AttributeInstance movementSpeedAttribute = yeti.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeedAttribute != null) {
            movementSpeedAttribute.setBaseValue(AlphaYetiConfig.alphaYetiMovementSpeed.get());
        }

        AttributeInstance attackDamageAttribute = yeti.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageAttribute != null) {
            attackDamageAttribute.setBaseValue(AlphaYetiConfig.alphaYetiAttackDamage.get());
        }

        AttributeInstance followRangeAttribute = yeti.getAttribute(Attributes.FOLLOW_RANGE);
        if (followRangeAttribute != null) {
            followRangeAttribute.setBaseValue(AlphaYetiConfig.alphaYetiFollowRange.get());
        }

        AttributeInstance knockbackResistanceAttribute = yeti.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
        if (knockbackResistanceAttribute != null) {
            knockbackResistanceAttribute.setBaseValue(AlphaYetiConfig.alphaYetiKnockbackResistance.get());
        }
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 317))
    private int modifyXpReward(int original) {
        return AlphaYetiConfig.alphaYetiXpReward.get();
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void modifyGoals(CallbackInfo ci) {
        AlphaYeti yeti = (AlphaYeti) (Object) this;

        if (!AlphaYetiConfig.alphaYetiCanThrowEntity.get()) {
            yeti.goalSelector.getAvailableGoals().removeIf(goal -> goal.getGoal() instanceof ThrowRiderGoal);
        }

        if (!AlphaYetiConfig.alphaYetiCanUseIceBomb.get()) {
            yeti.goalSelector.getAvailableGoals().removeIf(goal -> goal.getGoal() instanceof RangedAttackGoal);
        }

        if (!AlphaYetiConfig.alphaYetiCanUseIceRampage.get()) {
            yeti.goalSelector.getAvailableGoals().removeIf(goal -> goal.getGoal() instanceof YetiRampageGoal);
            yeti.goalSelector.getAvailableGoals().removeIf(goal -> goal.getGoal() instanceof YetiTiredGoal);
        }
    }
}