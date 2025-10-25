package com.xiaoyu.suixingxiugai.mixin.server.mutantmonsters.entity;

import com.xiaoyu.suixingxiugai.config.mutantmonsters.entity.MutantZombieConfig;

import fuzs.mutantmonsters.world.entity.mutant.MutantZombie;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;

@Mixin(MutantZombie.class)
public abstract class MutantZombieMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void suixingxiugai$init(EntityType<? extends MutantZombie> type, Level worldIn, CallbackInfo ci) {
        MutantZombie mutantZombie = (MutantZombie) (Object) this;
        try {
            Field livesField = MutantZombie.class.getDeclaredField("LIVES");
            livesField.setAccessible(true);
            mutantZombie.getEntityData().set(
                (EntityDataAccessor<Integer>) livesField.get(null), 
                MutantZombieConfig.MUTANT_ZOMBIE_RESURRECTION_COUNT.get() - 1
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Inject(method = "doHurtTarget", at = @At("HEAD"), cancellable = true)
    private void suixingxiugai$doHurtTarget(Entity entityIn, CallbackInfoReturnable<Boolean> cir) {
        MutantZombie mutantZombie = (MutantZombie) (Object) this;
        
        if (mutantZombie.isAnimationPlaying()) {
            return;
        }
        
        boolean enableSlamGroundAttack = MutantZombieConfig.MUTANT_ZOMBIE_ENABLE_SLAM_GROUND_ATTACK.get();
        boolean enableThrowAttack = MutantZombieConfig.MUTANT_ZOMBIE_ENABLE_THROW_ATTACK.get();
        boolean shouldUseSlamGround = shouldUseSlamGroundAttack(mutantZombie, entityIn);

        if (shouldUseSlamGround && enableSlamGroundAttack) {
            mutantZombie.setAnimation(MutantZombie.SLAM_GROUND_ANIMATION);
        } else if (!shouldUseSlamGround && enableThrowAttack) {
            mutantZombie.setAnimation(MutantZombie.THROW_ANIMATION);
        } else if (shouldUseSlamGround && !enableSlamGroundAttack && enableThrowAttack) {
            mutantZombie.setAnimation(MutantZombie.THROW_ANIMATION);
        } else if (!shouldUseSlamGround && !enableThrowAttack && enableSlamGroundAttack) {
            mutantZombie.setAnimation(MutantZombie.SLAM_GROUND_ANIMATION);
        }

        cir.setReturnValue(true);
    }
    
    @Inject(method = "customServerAiStep", at = @At("HEAD"), cancellable = true)
    private void suixingxiugai$customServerAiStep(CallbackInfo ci) {
        if (!MutantZombieConfig.MUTANT_ZOMBIE_ENABLE_SLAM_GROUND_ATTACK.get()) {
            ci.cancel();
        }
    }

    @Redirect(
        method = "registerGoals", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V", 
            ordinal = 1
        )
    )
    private void suixingxiugai$redirectAddRoarGoal(GoalSelector instance, int priority, Goal goal) {
        if (MutantZombieConfig.MUTANT_ZOMBIE_ENABLE_ROAR_ATTACK.get()) {
            instance.addGoal(priority, goal);
        }
    }

    private boolean shouldUseSlamGroundAttack(MutantZombie mutantZombie, Entity entityIn) {
        if (entityIn.getVehicle() != mutantZombie && mutantZombie.getRandom().nextInt(5) != 0) {
            return mutantZombie.onGround() || !mutantZombie.getFeetBlockState().getFluidState().isEmpty();
        }
        return false;
    }
}