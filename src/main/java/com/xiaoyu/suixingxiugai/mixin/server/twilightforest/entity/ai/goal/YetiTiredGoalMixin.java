package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.AlphaYetiConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import twilightforest.entity.ai.goal.YetiTiredGoal;
import twilightforest.entity.boss.AlphaYeti;

@Mixin(AlphaYeti.class)
public class YetiTiredGoalMixin {

    @Redirect(
        method = "registerGoals", 
        at = @At(
            value = "NEW", 
            target = "twilightforest/entity/ai/goal/YetiTiredGoal"
        )
    )
    private YetiTiredGoal redirectYetiTiredGoalConstructor(AlphaYeti yeti, int duration) {
        return new YetiTiredGoal(yeti, AlphaYetiConfig.alphaYetiIceRampageTiredDuration.get());
    }
}