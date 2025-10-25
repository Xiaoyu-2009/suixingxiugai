package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityLightningDragon;
import com.github.alexthe666.iceandfire.entity.IafDragonLogic;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(IafDragonLogic.class)
public class DragonLogicForceSleepMixin {
    
    @Inject(
        method = "updateDragonServer",
        at = @At(
            value = "INVOKE",
            target = "Lcom/github/alexthe666/iceandfire/entity/EntityDragonBase;setInSittingPose(Z)V",
            ordinal = 0
        ),
        cancellable = true
    )
    private void onSetInSittingPoseForSleep(CallbackInfo ci) {
        IafDragonLogic logic = (IafDragonLogic) (Object) this;

        try {
            Field dragonField = IafDragonLogic.class.getDeclaredField("dragon");
            dragonField.setAccessible(true);
            EntityDragonBase dragon = (EntityDragonBase) dragonField.get(logic);

            if (dragon instanceof EntityLightningDragon) {
                if (!DragonConfig.lightningDragonSleeping.get()) {
                    ci.cancel();
                }
            }
        } catch (Exception e) {}
    }
}