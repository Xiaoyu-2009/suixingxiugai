package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityHydra;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.HydraConfig;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EntityHydra.class)
public abstract class HydraDamageMixin extends Monster {
    
    @Unique
    private float cumulativeDamage = 0.0f;

    protected HydraDamageMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void onHydraHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        double singleHitThreshold = HydraConfig.singleHitDamageToKill.get();
        if (amount >= singleHitThreshold) {
            EntityHydra hydra = (EntityHydra) (Object) this;
            hydra.setHealth(0.0f);
            return;
        }

        cumulativeDamage += amount;
        double cumulativeThreshold = HydraConfig.cumulativeDamageToKill.get();
        if (cumulativeDamage >= cumulativeThreshold) {
            EntityHydra hydra = (EntityHydra) (Object) this;
            hydra.setHealth(0.0f);
            return;
        }

        List<? extends String> damageTypesToKill = HydraConfig.damageTypesToKill.get();
        String damageType = source.type().msgId();
        for (String killType : damageTypesToKill) {
            if (damageType.equals(killType)) {
                EntityHydra hydra = (EntityHydra) (Object) this;
                hydra.setHealth(0.0f);
                return;
            }
        }
    }
}