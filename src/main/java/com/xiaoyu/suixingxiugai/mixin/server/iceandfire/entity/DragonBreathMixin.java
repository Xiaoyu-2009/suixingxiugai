package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.entity.EntityLightningDragon;
import com.xiaoyu.suixingxiugai.util.iceandfire.entity.DragonConfigHelper;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityFireDragon.class, EntityIceDragon.class, EntityLightningDragon.class})
public abstract class DragonBreathMixin extends LivingEntity {

    private DragonBreathMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }
    
    @Inject(method = "aiStep", at = @At("HEAD"))
    private void onAiTick(CallbackInfo ci) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;

        if (!DragonConfigHelper.canDragonBreath(dragon)) {
            if (dragon.isBreathingFire()) {
                dragon.setBreathingFire(false);
            }
        }
    }

    @Inject(
        method = "riderShootFire(Lnet/minecraft/world/entity/Entity;)V", 
        at = @At("HEAD"), 
        cancellable = true, 
        remap = false
    )
    private void onRiderShootFire(Entity controller, CallbackInfo ci) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;

        if (!DragonConfigHelper.canDragonBreath(dragon)) {
            ci.cancel();
        }
    }

    @Inject(
        method = "stimulateFire(DDDI)V", 
        at = @At("HEAD"), 
        cancellable = true, 
        remap = false
    )
    private void onStimulateFire(double burnX, double burnY, double burnZ, int syncType, CallbackInfo ci) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;

        if (!DragonConfigHelper.canDragonBreath(dragon)) {
            ci.cancel();
        }
    }
}