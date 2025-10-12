package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.entity.EntityDragonEgg;
import com.github.alexthe666.iceandfire.enums.EnumDragonEgg;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonEggConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityDragonEgg.class)
public class EntityDragonEggMixin {
    
    @Unique
    private static DragonType suixingxiugai$currentDragonType;

    @Redirect(
        method = "*",
        at = @At(
            value = "INVOKE",
            target = "Lcom/github/alexthe666/iceandfire/entity/EntityDragonEgg;getEggType()Lcom/github/alexthe666/iceandfire/enums/EnumDragonEgg;"
        )
    )
    private EnumDragonEgg captureDragonType(EntityDragonEgg egg) {
        EnumDragonEgg eggType = egg.getEggType();
        suixingxiugai$currentDragonType = eggType.dragonType;
        return eggType;
    }

    @Redirect(
        method = "*",
        at = @At(
            value = "FIELD", 
            target = "Lcom/github/alexthe666/iceandfire/IafConfig;dragonEggTime:I"
        )
    )
    private int getDragonEggHatchTime() {
        if (suixingxiugai$currentDragonType == null) {
            return DragonEggConfig.fireDragonEggHatchTime.get();
        }

        int typeId = suixingxiugai$currentDragonType.getIntFromType();
        switch (typeId) {
            case 0:
                return DragonEggConfig.fireDragonEggHatchTime.get();
/*            case 1:
                return DragonEggConfig.iceDragonEggHatchTime.get();*/
            case 2:
                return DragonEggConfig.lightningDragonEggHatchTime.get();
            default:
                return DragonEggConfig.fireDragonEggHatchTime.get();
        }
    }
}