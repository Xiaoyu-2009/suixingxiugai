package com.xiaoyu.suixingxiugai.mixin.server.alex.entity;

import com.github.alexthe666.alexsmobs.entity.EntityTendonSegment;
import com.xiaoyu.suixingxiugai.config.alex.item.TendonWhipConfig;

import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityTendonSegment.class)
public class EntityTendonSegmentMixin {
    
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 3))
    private int modifyWhipMaxTargets(int original) {
        return TendonWhipConfig.whipMaxTargets.get();
    }
    
    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 8.0D))
    private double modifyWhipChainRange(double original) {
        return TendonWhipConfig.whipAttackRange.get();
    }
    
    @Inject(
        method = "getDamageFor(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)D", 
        at = @At("HEAD"), 
        cancellable = true, 
        remap = false
    )
    private void modifyWhipMaxDamage(LivingEntity creator, LivingEntity entity, CallbackInfoReturnable<Double> cir) {
        double maxDamage = TendonWhipConfig.whipMaxDamage.get();
        cir.setReturnValue(maxDamage);
    }
}