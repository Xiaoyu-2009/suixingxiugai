package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;
import com.xiaoyu.suixingxiugai.util.twilightforest.entity.LichPhaseUtil;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import twilightforest.entity.ai.goal.LichPopMobsGoal;
import twilightforest.entity.boss.Lich;

@Mixin(LichPopMobsGoal.class)
public class LichPopMobsGoalMixin {
    
    @Shadow
    private Lich lich;
    
    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void suixingxiugai$controlLichPopMobs(CallbackInfoReturnable<Boolean> cir) {
        LichPhaseUtil.checkAndControlPhase2And3MobAbsorption(this.lich, cir);
    }
    
    @ModifyArg(
        method = "tick",
        at = @At(
            value = "INVOKE",
            target = "Ltwilightforest/entity/boss/Lich;heal(F)V"
        ),
        index = 0
    )
    private float suixingxiugai$modifyLichPopMobsHealAmount(float originalHealAmount) {
        int phase = this.lich.getPhase();

        if (phase == 2) {
            return (float) LichConfig.lichPhase2MobAbsorbHealAmount.get().doubleValue();
        } else if (phase == 3) {
            return (float) LichConfig.lichPhase3MobAbsorbHealAmount.get().doubleValue();
        }

        return originalHealAmount;
    }
}