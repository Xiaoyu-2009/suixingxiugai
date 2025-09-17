package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import com.xiaoyu.suixingxiugai.gamerule.SuixingxiugaiGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityDragonBase.class)
public class DragonEntityMixin {
    @Inject(method = "wantsToAttack(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)Z",
            at = @At("HEAD"), cancellable = true)
    private void onWantsToAttack(LivingEntity target, LivingEntity owner, CallbackInfoReturnable<Boolean> cir) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;

        if (dragon.isTame() && target instanceof Player && dragon.isOwnedBy(target)) {
            if (!dragon.level().getGameRules().getBoolean(SuixingxiugaiGameRules.ICE_AND_FIRE_DRAGON_PVP)) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }

    @Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
    private void onHurt(DamageSource dmg, float i, CallbackInfoReturnable<Boolean> cir) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;

        if (dragon.isTame() && dmg.getEntity() != null && dragon.getOwner() != null && dmg.getEntity().equals(dragon.getOwner())) {
            if (!dragon.level().getGameRules().getBoolean(SuixingxiugaiGameRules.ICE_AND_FIRE_DRAGON_PVP)) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }
}