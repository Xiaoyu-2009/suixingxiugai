package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.AlphaYetiConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import twilightforest.entity.ai.goal.YetiRampageGoal;

@Mixin(YetiRampageGoal.class)
public class YetiRampageGoalFallingIceMixin {

    @Inject(
        method = "tick", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/AlphaYeti;makeRandomBlockFall(II)V", 
            ordinal = 0
        ),
        cancellable = true
    )
    private void onMakeRandomBlockFallFirst(CallbackInfo ci) {
        if (!AlphaYetiConfig.alphaYetiCanUseFallingIce.get()) {
            ci.cancel();
        }
    }

    @Inject(
        method = "tick", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/AlphaYeti;makeRandomBlockFall(II)V", 
            ordinal = 1
        ),
        cancellable = true
    )
    private void onMakeRandomBlockFallSecond(CallbackInfo ci) {
        if (!AlphaYetiConfig.alphaYetiCanUseFallingIce.get()) {
            ci.cancel();
        }
    }

    @Inject(
        method = "tick", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/AlphaYeti;makeBlockAboveTargetFall()V"
        ),
        cancellable = true
    )
    private void onMakeBlockAboveTargetFall(CallbackInfo ci) {
        if (!AlphaYetiConfig.alphaYetiCanUseFallingIce.get()) {
            ci.cancel();
        }
    }
}