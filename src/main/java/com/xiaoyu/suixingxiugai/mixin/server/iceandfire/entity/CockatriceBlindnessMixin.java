package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityCockatrice;
import com.xiaoyu.suixingxiugai.util.iceandfire.entity.GazeImmunityHelper;

import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityCockatrice.class)
public class CockatriceBlindnessMixin {

    @Inject(method = "shouldMelee", at = @At("HEAD"), cancellable = true, remap = false)
    private void onShouldMelee(CallbackInfoReturnable<Boolean> cir) {
        EntityCockatrice cockatrice = (EntityCockatrice) (Object) this;
        LivingEntity target = cockatrice.getTarget();

        if (target != null && GazeImmunityHelper.isImmuneToGazeAttack(target)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getTargetedEntity", at = @At("HEAD"), cancellable = true, remap = false)
    private void onGetTargetedEntity(CallbackInfoReturnable<LivingEntity> cir) {
        EntityCockatrice cockatrice = (EntityCockatrice) (Object) this;
        LivingEntity target = cockatrice.getTarget();

        if (target != null && GazeImmunityHelper.isImmuneToGazeAttack(target)) {
            cir.setReturnValue(null);
        }
    }
}