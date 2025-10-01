package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityGorgon;
import com.xiaoyu.suixingxiugai.util.iceandfire.entity.GazeImmunityHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.LivingEntity;

@Mixin(EntityGorgon.class)
public class GorgonBlindnessMixin {

    @Redirect(
        method = "doHurtTarget(Lnet/minecraft/world/entity/Entity;)Z", 
        at = @At(
            value = "INVOKE", 
            target = "Lcom/github/alexthe666/iceandfire/entity/EntityGorgon;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"
        )
    )
    private boolean redirectHasEffect(EntityGorgon gorgon, net.minecraft.world.effect.MobEffect effect) {
        if (GazeImmunityHelper.isGorgonBlindedByWearingImmunityItems(gorgon) && 
            effect == net.minecraft.world.effect.MobEffects.BLINDNESS) {
            return true;
        }
        
        return gorgon.hasEffect(effect);
    }

    @Redirect(
        method = "setTarget(Lnet/minecraft/world/entity/LivingEntity;)V", 
        at = @At(
            value = "INVOKE", 
            target = "Lcom/github/alexthe666/iceandfire/entity/EntityGorgon;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"
        )
    )
    private boolean redirectHasEffectSetTarget(EntityGorgon gorgon, net.minecraft.world.effect.MobEffect effect) {
        if (GazeImmunityHelper.isGorgonBlindedByWearingImmunityItems(gorgon) && 
            effect == net.minecraft.world.effect.MobEffects.BLINDNESS) {
            return true;
        }
        
        return gorgon.hasEffect(effect);
    }

    @Inject(
        method = "isBlindfolded(Lnet/minecraft/world/entity/LivingEntity;)Z",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    private static void onIsBlindfolded(LivingEntity attackTarget, CallbackInfoReturnable<Boolean> cir) {
        if (attackTarget != null && GazeImmunityHelper.isImmuneToGazeAttack(attackTarget)) {
            cir.setReturnValue(true);
        }
    }
}