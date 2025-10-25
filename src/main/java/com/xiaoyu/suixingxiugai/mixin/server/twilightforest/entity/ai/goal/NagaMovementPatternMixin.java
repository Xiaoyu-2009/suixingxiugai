package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.NagaConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import twilightforest.entity.ai.goal.NagaMovementPattern;

@Mixin(value = NagaMovementPattern.class, remap = false)
public class NagaMovementPatternMixin {
    
    @ModifyConstant(method = "doDaze", constant = @Constant(intValue = 60))
    private int modifyStunDurationBase(int original) {
        return NagaConfig.nagaStunDuration.get();
    }
}