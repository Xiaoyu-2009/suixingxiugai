package com.xiaoyu.suixingxiugai.mixin.server.alex.entity;

import com.github.alexthe666.alexsmobs.entity.EntityKomodoDragon;
import com.xiaoyu.suixingxiugai.config.alex.entity.KomodoDragonConfig;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.damagesource.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityKomodoDragon.class)
public abstract class EntityKomodoDragonMixin extends TamableAnimal {

    private boolean hasBeenAttacked = false;

    protected EntityKomodoDragonMixin(EntityType<? extends TamableAnimal> entityType, net.minecraft.world.level.Level level) {
        super(entityType, level);
    }

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void onSetTarget(LivingEntity target, CallbackInfo ci) {
        if (!KomodoDragonConfig.komodoDragonWillAttack.get() && !hasBeenAttacked) {

            boolean isDefensive = isDefensiveTarget(target);
            
            if (!isDefensive) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"))
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!KomodoDragonConfig.komodoDragonWillAttack.get()) {
            hasBeenAttacked = true;
        }
    }
    
    @Inject(method = "doHurtTarget", at = @At("HEAD"), cancellable = true)
    private void onDoHurtTarget(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!KomodoDragonConfig.komodoDragonWillAttack.get() && !hasBeenAttacked) {

            boolean isDefensive = false;

            if (entity instanceof LivingEntity) {
                isDefensive = isDefensiveTarget((LivingEntity) entity);
            }

            if (!isDefensive) {
                cir.cancel();
                cir.setReturnValue(false);
            }
        }
    }

    private boolean isDefensiveTarget(LivingEntity target) {
        if (target == null) {
            return false;
        }

        if (this.getOwner() != null && target == this.getOwner().getLastHurtMob()) {
            return true;
        }

        if (target == this.getLastHurtByMob()) {
            return true;
        }

        return false;
    }
}