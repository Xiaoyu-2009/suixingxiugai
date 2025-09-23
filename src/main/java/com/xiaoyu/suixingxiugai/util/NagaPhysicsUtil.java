package com.xiaoyu.suixingxiugai.util;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.naga.NagaConfig;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import twilightforest.entity.boss.Naga;

public class NagaPhysicsUtil {
    
    public static void applyHeadPushAndRecoilForces(Naga naga, Entity target) {
        applyPushAndRecoilForces(naga, target, 
            NagaConfig.nagaHeadChargePushForce.get(), 
            NagaConfig.nagaHeadChargeRecoilForce.get());
    }
    
    public static void applySegmentPushAndRecoilForces(Naga naga, Entity target) {
        applyPushAndRecoilForces(naga, target, 
            NagaConfig.nagaSegmentChargePushForce.get(), 
            NagaConfig.nagaSegmentChargeRecoilForce.get());
    }
    
    private static void applyPushAndRecoilForces(Naga naga, Entity target, double pushForce, double recoilForce) {
        float yRot = naga.getYRot();

        double pushX = -Mth.sin((yRot * Mth.PI) / 180.0F) * pushForce;
        double pushY = 0.4F * pushForce;
        double pushZ = Mth.cos((yRot * Mth.PI) / 180.0F) * pushForce;
        
        target.push(pushX, pushY, pushZ);
        
        double recoilX = -Mth.sin((yRot * Mth.PI) / 180.0F) * recoilForce;
        double recoilY = 0.4F * recoilForce;
        double recoilZ = Mth.cos((yRot * Mth.PI) / 180.0F) * recoilForce;
        
        naga.push(recoilX, recoilY, recoilZ);
    }

    public static boolean isEntityWithinNagaCourtyard(Naga naga, Entity entity) {
        if (!naga.isRestrictionPointValid(naga.level().dimension())) {
            return true;
        } else {
            double distX = Math.abs(naga.getRestrictionPoint().pos().getX() - entity.blockPosition().getX());
            double distY = Math.abs(naga.getRestrictionPoint().pos().getY() - entity.blockPosition().getY());
            double distZ = Math.abs(naga.getRestrictionPoint().pos().getZ() - entity.blockPosition().getZ());

            return distX <= NagaConfig.nagaCourtyardBoundX.get() 
            && distY <= NagaConfig.nagaCourtyardBoundY.get() 
            && distZ <= NagaConfig.nagaCourtyardBoundZ.get();
        }
    }
}