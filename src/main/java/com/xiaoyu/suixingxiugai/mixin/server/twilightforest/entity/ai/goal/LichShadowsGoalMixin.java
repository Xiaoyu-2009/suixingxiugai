package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import twilightforest.entity.ai.goal.LichShadowsGoal;
import twilightforest.entity.boss.Lich;

@Mixin(LichShadowsGoal.class)
public class LichShadowsGoalMixin {
    
    @Shadow
    private Lich lich;
    
    @Inject(
        method = "tick", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/ai/goal/LichShadowsGoal;checkAndSpawnClones()V"
        ), 
        cancellable = true
    )
    private void suixingxiugai$controlShadowCloneSpawning(CallbackInfo ci) {
        if (!LichConfig.lichCanSpawnPhantom.get()) {
            ci.cancel();
            return;
        }

        if (this.lich != null && !this.lich.isShadowClone()) {
            int currentClones = this.lich.countMyClones();
            int maxClones = LichConfig.lichPhantomSummonMaxCount.get();
            if (currentClones >= maxClones) {
                ci.cancel();
            }
        }
    }
}