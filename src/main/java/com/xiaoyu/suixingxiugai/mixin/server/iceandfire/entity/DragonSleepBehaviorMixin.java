package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;

import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityDragonBase.class)
public class DragonSleepBehaviorMixin {
    
    @Redirect(
        method = "isTimeToWake",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;isDay()Z"
        )
    )
    private boolean redirectIsDay(Level level) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;

        if (dragon instanceof EntityFireDragon) {
            if (!DragonConfig.fireDragonSleeping.get()) {
                return true;
            }
        } else if (dragon instanceof EntityIceDragon) {
            if (!DragonConfig.iceDragonSleeping.get()) {
                return true;
            }
        }/*  else if (dragon instanceof EntityLightningDragon) {
            if (!DragonConfig.lightningDragonSleeping.get()) {
                return true;
            }
            return level.isNight();
        } */

        return level.isDay();
    }
}