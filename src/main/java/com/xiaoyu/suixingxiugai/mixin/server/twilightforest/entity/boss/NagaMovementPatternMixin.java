package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;

@Mixin(targets = "twilightforest.entity.ai.goal.NagaMovementPattern", remap = false)
public class NagaMovementPatternMixin {
    
    @ModifyConstant(method = "doDaze", constant = @Constant(intValue = 60))
    private int modifyStunDurationBase(int original) {
        return SuixingxiugaiConfig.nagaStunDuration.get();
    }
}