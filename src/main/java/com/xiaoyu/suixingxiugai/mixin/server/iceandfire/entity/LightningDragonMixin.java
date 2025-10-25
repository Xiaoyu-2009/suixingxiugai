package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityLightningDragon;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLightningDragon.class)
public class LightningDragonMixin {
    
    @Inject(
        method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V",
        at = @At("TAIL")
    )
    private void onInit(EntityType<? extends EntityLightningDragon> type, Level worldIn, CallbackInfo ci) {
        EntityLightningDragon dragon = (EntityLightningDragon) (Object) this;
        dragon.setInSittingPose(DragonConfig.lightningDragonSleeping.get());
    }
}