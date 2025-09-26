package com.xiaoyu.suixingxiugai.mixin.server.alex;

import com.github.alexthe666.alexsmobs.item.ItemTendonWhip;
import com.xiaoyu.suixingxiugai.config.alex.item.TendonWhipConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemTendonWhip.class)
public class TendonWhipMixin {
    
    @Redirect(
        method = "onLeftClick", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/phys/Vec3;scale(D)Lnet/minecraft/world/phys/Vec3;"
        )
    )
    private Vec3 modifyWhipAttackRange1(Vec3 vec3, double scale) {
        double attackRange = TendonWhipConfig.whipAttackRange.get();
        return vec3.scale(attackRange);
    }
    
    @Redirect(
        method = "onLeftClick", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/entity/LivingEntity;getBoundingBox()Lnet/minecraft/world/phys/AABB;"
        )
    )
    private net.minecraft.world.phys.AABB modifyWhipAttackRange2(LivingEntity entity) {
        double attackRange = TendonWhipConfig.whipAttackRange.get();
        return entity.getBoundingBox().inflate(attackRange);
    }
    
    @Inject(method = "isCharged", at = @At("HEAD"), cancellable = true, remap = false)
    private void modifyWhipChargeRequirement(Player player, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (!TendonWhipConfig.whipRequireFullCharge.get()) {
            cir.setReturnValue(true);
        }
    }
}