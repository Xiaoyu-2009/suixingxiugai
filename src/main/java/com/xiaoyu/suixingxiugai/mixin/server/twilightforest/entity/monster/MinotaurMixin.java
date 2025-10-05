package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.monster;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.MinoshroomConfig;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Difficulty;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.SpawnGroupData;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import twilightforest.entity.monster.Minotaur;
import twilightforest.entity.boss.Minoshroom;

import java.util.Objects;

@Mixin(Minotaur.class)
public class MinotaurMixin {

    @ModifyConstant(method = "doHurtTarget", constant = @Constant(doubleValue = 0.35D))
    private double modifyChargeKnockbackHeight(double original) {
        return MinoshroomConfig.minoshroomChargeKnockbackHeight.get();
    }
    
    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void addDifficultyAttackDamageBoost(ServerLevelAccessor accessor, DifficultyInstance difficulty, net.minecraft.world.entity.MobSpawnType reason, SpawnGroupData data, CompoundTag tag, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (!((Object) this instanceof Minoshroom)) {
            return;
        }
        
        Minoshroom minoshroom = (Minoshroom) (Object) this;
        
        if (minoshroom.level().getDifficulty() != Difficulty.EASY && minoshroom.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
            boolean hard = minoshroom.level().getDifficulty() == Difficulty.HARD;
            AttributeModifier modifier = new AttributeModifier("Difficulty Attack Damage Boost", hard ? MinoshroomConfig.minoshroomDifficultyAttackDamageBoostHard.get() : MinoshroomConfig.minoshroomDifficultyAttackDamageBoostNormal.get(), AttributeModifier.Operation.ADDITION);
            if (!Objects.requireNonNull(minoshroom.getAttribute(Attributes.ATTACK_DAMAGE)).hasModifier(modifier)) {
                Objects.requireNonNull(minoshroom.getAttribute(Attributes.ATTACK_DAMAGE)).addPermanentModifier(modifier);
            }
        }
    }
}