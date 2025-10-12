package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonEgg;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonEggConfig;

import net.minecraft.util.RandomSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityDragonEgg.class)
public class EntityDragonEggFreezeMixin {
    
    @Redirect(
        method = "*",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/RandomSource;nextInt(I)I",
            ordinal = 0
        )
    )
    private int modifyIceDragonEggFreezeProbability(RandomSource randomSource, int bound) {
        int freezeProbability = DragonEggConfig.iceDragonEggFreezeProbability.get();
        return randomSource.nextInt(freezeProbability);
    }
}