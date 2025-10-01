package com.xiaoyu.suixingxiugai.util.twilightforest.entity;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import twilightforest.entity.boss.Lich;

public class LichPhaseUtil {

    public static void checkAndControlPhase2MinionAbsorption(Lich lich, CallbackInfoReturnable<Boolean> cir) {
        commonAbsorptionCheck(lich, cir, true, false);
    }

    public static void checkAndControlPhase2And3MobAbsorption(Lich lich, CallbackInfoReturnable<Boolean> cir) {
        commonAbsorptionCheck(lich, cir, true, true);
    }

    private static void commonAbsorptionCheck(Lich lich, CallbackInfoReturnable<Boolean> cir, 
        boolean checkPhase2Mob, boolean checkPhase3) {
        if (!LichConfig.lichCanAbsorbMinions.get()) {
            cir.cancel();
            cir.setReturnValue(false);
            return;
        }

        int phase = lich.getPhase();

        if (phase == 2 && lich.getHealth() >= LichConfig.lichPhase2MinionAbsorbHealthThreshold.get()) {
            cir.cancel();
            cir.setReturnValue(false);
            return;
        }

        if (checkPhase2Mob && phase == 2 && lich.getHealth() >= LichConfig.lichPhase2MobAbsorbHealthThreshold.get()) {
            cir.cancel();
            cir.setReturnValue(false);
            return;
        }

        if (checkPhase3 && phase == 3 && lich.getHealth() >= LichConfig.lichPhase3MobAbsorbHealthThreshold.get()) {
            cir.cancel();
            cir.setReturnValue(false);
            return;
        }

        if (phase == 2 && !LichConfig.lichCanAbsorbMinionsInPhase2.get()) {
            cir.cancel();
            cir.setReturnValue(false);
            return;
        }

        if (checkPhase2Mob && phase == 2 && !LichConfig.lichCanAbsorbMobsInPhase2.get()) {
            cir.cancel();
            cir.setReturnValue(false);
            return;
        }

        if (checkPhase3 && phase == 3 && !LichConfig.lichCanAbsorbMobsInPhase3.get()) {
            cir.cancel();
            cir.setReturnValue(false);
            return;
        }
    }
}