package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.MinoshroomConfig;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import twilightforest.entity.ai.goal.GroundAttackGoal;
import twilightforest.entity.boss.Minoshroom;

@Mixin(GroundAttackGoal.class)
public class GroundAttackGoalMixin {
    
    @Shadow
    private Minoshroom attacker;
    
    @ModifyConstant(method = "start", constant = @Constant(intValue = 30))
    private int modifyBaseChargeTime(int original) {
        return MinoshroomConfig.minoshroomGroundAttackChargeTime.get();
    }
    
    @Redirect(
        method = "tick", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
        )
    )
    private boolean redirectHurtCall(Entity entity, net.minecraft.world.damagesource.DamageSource source, float amount) {
        if (entity.onGround()) {
            double attackDamage = this.attacker.getAttributeValue(Attributes.ATTACK_DAMAGE);
            float modifiedAmount = (float) (attackDamage * MinoshroomConfig.minoshroomGroundAttackDamageMultiplier.get());
            return entity.hurt(source, modifiedAmount);
        }
        return entity.hurt(source, amount);
    }
}