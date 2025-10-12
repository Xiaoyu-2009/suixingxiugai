package com.xiaoyu.suixingxiugai.mixin.server.alex.entity;

import com.github.alexthe666.alexsmobs.entity.EntityMosquitoSpit;
import com.xiaoyu.suixingxiugai.config.alex.item.BloodSprayerConfig;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityMosquitoSpit.class)
public class EntityMosquitoSpitMixin {
    
    @Redirect(
        method = "onEntityHit",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
        )
    )
    private boolean modifyBloodSprayerDamage(Entity target, DamageSource source, float amount) {
        float damage = BloodSprayerConfig.bloodSprayerDamage.get().floatValue();
        return target.hurt(source, damage);
    }
}