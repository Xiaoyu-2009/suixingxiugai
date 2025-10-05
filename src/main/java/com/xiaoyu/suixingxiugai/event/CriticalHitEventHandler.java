package com.xiaoyu.suixingxiugai.event;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

public class CriticalHitEventHandler {
    private static Entity lastCriticalTarget = null;
    private static long lastCriticalTime = 0;

    public static void onCriticalHit(CriticalHitEvent event) {
        if (!event.isCanceled() && event.getDamageModifier() > 1.0f) {
            lastCriticalTarget = event.getTarget();
            lastCriticalTime = System.currentTimeMillis();
        }
    }

    public static boolean isLastAttackCritical(Entity target) {
        boolean isCrit = lastCriticalTarget == target && (System.currentTimeMillis() - lastCriticalTime) < 200;

        if (isCrit) {
            return true;
        }

        if ((System.currentTimeMillis() - lastCriticalTime) >= 200) {
            clearCriticalRecord();
        }
        
        return false;
    }

    private static void clearCriticalRecord() {
        lastCriticalTarget = null;
        lastCriticalTime = 0;
    }
}