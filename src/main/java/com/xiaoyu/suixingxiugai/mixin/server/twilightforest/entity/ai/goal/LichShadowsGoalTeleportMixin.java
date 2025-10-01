package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.util.twilightforest.entity.LichTeleportUtil;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import twilightforest.entity.ai.goal.LichShadowsGoal;
import twilightforest.entity.boss.Lich;

@Mixin(LichShadowsGoal.class)
public class LichShadowsGoalTeleportMixin {
    
    @Shadow
    private Lich lich;
    
    @Inject(
        method = "tick", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/Lich;teleportToSightOfEntity(Lnet/minecraft/world/entity/Entity;)V"
        ), 
        cancellable = true
    )
    private void suixingxiugai$controlLichPhaseTeleport(CallbackInfo ci) {
        LichTeleportUtil.checkAndCancelTeleport(this.lich, ci);
    }
}