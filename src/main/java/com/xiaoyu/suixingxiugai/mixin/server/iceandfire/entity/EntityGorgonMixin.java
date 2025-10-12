package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityGorgon;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.GorgonConfig;
import com.xiaoyu.suixingxiugai.util.iceandfire.entity.GazeImmunityHelper;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityGorgon.class)
public class EntityGorgonMixin {

    @Redirect(
        method = "doHurtTarget(Lnet/minecraft/world/entity/Entity;)Z", 
        at = @At(
            value = "INVOKE", 
            target = "Lcom/github/alexthe666/iceandfire/entity/EntityGorgon;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"
        )
    )
    private boolean redirectHasEffect(EntityGorgon gorgon, MobEffect effect) {
        if (GazeImmunityHelper.isGorgonBlindedByWearingImmunityItems(gorgon) && 
            effect == MobEffects.BLINDNESS) {
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
    private boolean redirectHasEffectSetTarget(EntityGorgon gorgon, MobEffect effect) {
        if (GazeImmunityHelper.isGorgonBlindedByWearingImmunityItems(gorgon) && 
            effect == MobEffects.BLINDNESS) {
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

    @Inject(
        method = "aiStep",
        at = @At(
            value = "INVOKE",
            target = "Lcom/github/alexthe666/iceandfire/entity/EntityGorgon;setAnimation(Lcom/github/alexthe666/citadel/animation/Animation;)V",
            ordinal = 0
        ),
        cancellable = true
    )
    private void onPetrifyAnimationSet(CallbackInfo ci) {
        if (!GorgonConfig.gorgonCanUsePetrify.get()) {
            ci.cancel();
        }
    }

    @Inject(
        method = "aiStep",
        at = @At(
            value = "INVOKE",
            target = "Lcom/github/alexthe666/iceandfire/entity/EntityGorgon;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"
        ),
        cancellable = true
    )
    private void onPetrifySoundPlay(CallbackInfo ci) {
        if (!GorgonConfig.gorgonCanUsePetrify.get()) {
            ci.cancel();
        }
    }

    @Redirect(
        method = "doHurtTarget(Lnet/minecraft/world/entity/Entity;)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"
        )
    )
    private boolean redirectAddPoisonEffect(LivingEntity target, MobEffectInstance effectInstance) {
        if (!GorgonConfig.gorgonCanUsePoisonFang.get()) {
            return false;
        }
        return target.addEffect(effectInstance);
    }
}