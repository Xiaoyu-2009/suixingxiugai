package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityGorgon;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.GorgonConfig;

import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityGorgon.class)
public class EntityGorgonMeleeAttackMixin {
    
    @Inject(
        method = "doHurtTarget(Lnet/minecraft/world/entity/Entity;)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onDoHurtTarget(Entity entityIn, CallbackInfoReturnable<Boolean> cir) {
        if (!GorgonConfig.gorgonCanUseMeleeAttack.get()) {
            cir.cancel();
        }
    }
}