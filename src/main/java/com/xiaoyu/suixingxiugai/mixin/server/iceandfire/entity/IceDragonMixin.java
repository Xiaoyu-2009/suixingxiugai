package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityIceDragon.class)
public class IceDragonMixin {
    
    @Inject(
        method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V",
        at = @At("TAIL")
    )
    private void onInit(EntityType<? extends EntityIceDragon> type, Level worldIn, CallbackInfo ci) {
        EntityIceDragon dragon = (EntityIceDragon) (Object) this;
        dragon.setInSittingPose(DragonConfig.iceDragonSleeping.get());
    }
}