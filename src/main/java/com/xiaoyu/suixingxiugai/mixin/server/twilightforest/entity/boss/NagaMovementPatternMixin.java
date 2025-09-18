package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.entity.ai.goal.NagaMovementPattern;

@Mixin(NagaMovementPattern.class)
public class NagaMovementPatternMixin {
    
    @Shadow
    private int stateCounter;
    
    @Shadow
    private NagaMovementPattern.MovementState state;

    @Inject(method = "tick", at = @At("HEAD"))
    private void modifyStunDurationBase(CallbackInfo ci) {
        if (this.state == NagaMovementPattern.MovementState.DAZE && this.stateCounter >= 60 && this.stateCounter <= 100) {
            int baseDuration = SuixingxiugaiConfig.nagaStunDuration.get();
            this.stateCounter = this.stateCounter - 60 + baseDuration;
        }
    }
}