package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.tile.TileEntityEggInIce;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonEggConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TileEntityEggInIce.class)
public class TileEntityEggInIceMixin {
    
    @Redirect(
        method = "*",
        at = @At(
            value = "FIELD",
            target = "Lcom/github/alexthe666/iceandfire/IafConfig;dragonEggTime:I"
        )
    )
    private static int getIceDragonEggHatchTime() {
        return DragonEggConfig.iceDragonEggHatchTime.get();
    }
}