package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.MinoshroomConfig;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Constant;

import twilightforest.entity.ai.goal.ChargeAttackGoal;
import twilightforest.entity.ai.goal.GroundAttackGoal;
import twilightforest.entity.boss.Minoshroom;

@Mixin(Minoshroom.class)
public class MinoshroomMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyAttributes(CallbackInfo ci) {
        Minoshroom minoshroom = (Minoshroom) (Object) this;

        AttributeInstance maxHealthAttribute = minoshroom.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            maxHealthAttribute.setBaseValue(MinoshroomConfig.minoshroomMaxHealth.get());
            if (minoshroom.getHealth() > MinoshroomConfig.minoshroomMaxHealth.get()) {
                minoshroom.setHealth((float) MinoshroomConfig.minoshroomMaxHealth.get().doubleValue());
            }
        }

        AttributeInstance knockbackResistanceAttribute = minoshroom.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
        if (knockbackResistanceAttribute != null) {
            knockbackResistanceAttribute.setBaseValue(MinoshroomConfig.minoshroomKnockbackResistance.get());
        }

        AttributeInstance movementSpeedAttribute = minoshroom.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeedAttribute != null) {
            movementSpeedAttribute.setBaseValue(MinoshroomConfig.minoshroomMovementSpeed.get());
        }

        AttributeInstance attackDamageAttribute = minoshroom.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageAttribute != null) {
            attackDamageAttribute.setBaseValue(MinoshroomConfig.minoshroomAttackDamage.get());
        }
    }
    
    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void modifyChargeGoal(CallbackInfo ci) {
        Minoshroom minoshroom = (Minoshroom) (Object) this;

        if (!MinoshroomConfig.minoshroomCanCharge.get()) {
            minoshroom.goalSelector.getAvailableGoals().removeIf(goal -> goal.getGoal() instanceof ChargeAttackGoal);
        }

        if (!MinoshroomConfig.minoshroomCanGroundAttack.get()) {
            minoshroom.goalSelector.getAvailableGoals().removeIf(goal -> goal.getGoal() instanceof GroundAttackGoal);
        }
    }
    
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 100))
    private int modifyXpReward(int original) {
        return MinoshroomConfig.minoshroomXpReward.get();
    }
}