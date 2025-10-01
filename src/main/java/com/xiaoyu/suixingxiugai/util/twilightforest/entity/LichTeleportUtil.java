package com.xiaoyu.suixingxiugai.util.twilightforest.entity;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import twilightforest.entity.boss.Lich;

public class LichTeleportUtil {

    public static void checkAndCancelTeleport(Lich lich, Object callback) {
        if (lich != null) {
            int phase = lich.getPhase();
            boolean shouldCancel = false;
            
            switch (phase) {
                case 1:
                    shouldCancel = !LichConfig.lichPhase1CanTeleport.get();
                    break;
                case 2:
                    shouldCancel = !LichConfig.lichPhase2CanTeleport.get();
                    break;
                case 3:
                    shouldCancel = !LichConfig.lichPhase3CanTeleport.get();
                    break;
            }
            
            if (shouldCancel) {
                if (callback instanceof CallbackInfo) {
                    ((CallbackInfo) callback).cancel();
                } else if (callback instanceof CallbackInfoReturnable) {
                    ((CallbackInfoReturnable<?>) callback).cancel();
                }
            }
        }
    }
}