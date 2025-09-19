package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;

import twilightforest.entity.boss.Naga;

@Mixin(Naga.class)
public class NagaMixin {
    
    @ModifyConstant(method = "customServerAiStep", constant = @Constant(intValue = 15))
    private int modifyStunDamageThreshold(int original) {
        return SuixingxiugaiConfig.nagaStunDamageThreshold.get();
    }
}