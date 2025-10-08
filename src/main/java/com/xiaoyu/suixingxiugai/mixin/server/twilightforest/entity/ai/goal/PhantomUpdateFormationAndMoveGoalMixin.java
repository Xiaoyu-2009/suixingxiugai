package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.KnightPhantomConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import twilightforest.entity.ai.goal.PhantomUpdateFormationAndMoveGoal;
import twilightforest.entity.boss.KnightPhantom;

@Mixin(PhantomUpdateFormationAndMoveGoal.class)
public class PhantomUpdateFormationAndMoveGoalMixin {
    
    @Shadow
    private KnightPhantom boss;

    @Inject(
        method = "tick", 
        at = @At(
            value = "FIELD", 
            target = "Ltwilightforest/entity/boss/KnightPhantom;noPhysics:Z", 
            ordinal = 0, 
            shift = At.Shift.AFTER
        )
    )
    private void onSetNoPhysics(CallbackInfo ci) {
        if (!KnightPhantomConfig.knightPhantomCanNoClip.get()) {
            this.boss.noPhysics = false;
        }
    }
}