package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.AlphaYetiConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import twilightforest.entity.ai.goal.YetiRampageGoal;
import twilightforest.entity.boss.AlphaYeti;

@Mixin(AlphaYeti.class)
public class YetiRampageGoalMixin {

    @Redirect(
        method = "registerGoals", 
        at = @At(
            value = "NEW", 
            target = "twilightforest/entity/ai/goal/YetiRampageGoal"
        )
    )
    private YetiRampageGoal redirectYetiRampageGoalConstructor(AlphaYeti yeti, int timeout, int duration) {
        return new YetiRampageGoal(yeti, 
            AlphaYetiConfig.alphaYetiIceRampageTimeout.get(), 
            AlphaYetiConfig.alphaYetiIceRampageDuration.get()
        );
    }
}