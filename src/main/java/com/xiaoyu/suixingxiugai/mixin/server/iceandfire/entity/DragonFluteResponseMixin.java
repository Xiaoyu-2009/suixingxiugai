package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityAmphithere;
import com.github.alexthe666.iceandfire.entity.EntityHippogryph;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.DragonFluteConfig;

import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityDragonBase.class, EntityAmphithere.class, EntityHippogryph.class})
public class DragonFluteResponseMixin {
    
    @Inject(
        method = "onHearFlute",
        at = @At("HEAD"),
        remap = false
    )
    private void onHearFlute(Player player, CallbackInfo ci) {
        Object entity = this;

        if (DragonFluteConfig.dragonFluteAffectsWildDragons.get()) {
            if (entity instanceof EntityDragonBase dragon && !dragon.isTame()) {
                if (dragon.isFlying() || dragon.isHovering()) {
                    dragon.setFlying(false);
                    dragon.setHovering(false);
                }
            } else if (entity instanceof EntityAmphithere amphithere && !amphithere.isTame()) {
                if (!amphithere.onGround()) {
                    amphithere.isFallen = true;
                }
            } else if (entity instanceof EntityHippogryph hippogryph && !hippogryph.isOwnedBy(player)) {
                if (hippogryph.isFlying() || hippogryph.isHovering()) {
                    hippogryph.setFlying(false);
                    hippogryph.setHovering(false);
                }
            }
        }
    }
}