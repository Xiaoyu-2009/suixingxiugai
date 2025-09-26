package com.xiaoyu.suixingxiugai.mixin.server.iceandfire;

import com.github.alexthe666.iceandfire.world.gen.WorldGenDragonCave;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.dragon.DragonConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WorldGenDragonCave.class)
public class DragonCaveSleepingMixin {
    
    @ModifyArg(
        method = "createDragon", 
        at = @At(
            value = "INVOKE", 
            target = "Lcom/github/alexthe666/iceandfire/entity/EntityDragonBase;setInSittingPose(Z)V"
        ), 
        index = 0
    )
    private boolean modifySetInSittingPoseArg(boolean sleeping) {
        if (DragonConfig.wildDragonDefaultSleeping.get()) {
            return true;
        } else {
            return DragonConfig.wildDragonDefaultSleepingUnderground.get();
        }
    }
}