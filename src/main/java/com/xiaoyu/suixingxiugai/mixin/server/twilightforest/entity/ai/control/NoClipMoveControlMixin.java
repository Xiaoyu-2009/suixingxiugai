package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.control;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.KnightPhantomConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import twilightforest.entity.ai.control.NoClipMoveControl;

@Mixin(NoClipMoveControl.class)
public class NoClipMoveControlMixin {
    
    @ModifyConstant(
        method = "tick",
        constant = @Constant(doubleValue = 0.1D)
    )
    private double modifyBaseSpeed(double original) {
        return KnightPhantomConfig.knightPhantomFlightSpeed.get();
    }
}