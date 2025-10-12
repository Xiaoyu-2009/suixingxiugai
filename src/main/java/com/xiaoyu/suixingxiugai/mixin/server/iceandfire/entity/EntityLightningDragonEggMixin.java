package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonEgg;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonEggConfig;

import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityDragonEgg.class)
public class EntityLightningDragonEggMixin {

    @Redirect(
        method = "updateEggCondition",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z",
            ordinal = 1
        )
    )
    private boolean redirectLightningSpawn(Level level, net.minecraft.world.entity.Entity entity) {
        if (entity.getClass().getSimpleName().contains("Lightning")) {
            boolean shouldSpawnLightning = DragonEggConfig.lightningDragonEggSpawnLightning.get();

            if (!shouldSpawnLightning) {
                return false;
            }
        }

        return level.addFreshEntity(entity);
    }
}