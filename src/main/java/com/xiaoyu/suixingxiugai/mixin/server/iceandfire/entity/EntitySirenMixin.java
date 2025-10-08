package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntitySiren;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.SirenConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntitySiren.class)
public class EntitySirenMixin {

    @Redirect(
        method = "*",
        at = @At(
            value = "INVOKE", 
            target = "Lcom/github/alexthe666/iceandfire/entity/EntitySiren;isSinging()Z"
        )
    )
    private boolean redirectIsSinging(EntitySiren siren) {
        if (!SirenConfig.sirenCanSing.get()) {
            return false;
        }
        
        return siren.isSinging();
    }
}