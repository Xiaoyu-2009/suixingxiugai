package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityCyclops;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.CyclopsConfig;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityCyclops.class)
public abstract class EntityCyclopsMixin extends Monster {

    protected EntityCyclopsMixin(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Redirect(
        method = "positionRider(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity$MoveFunction;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
        )
    )
    private boolean redirectBiteDamage(Entity entity, DamageSource source, float amount) {
        return entity.hurt(source, (float) CyclopsConfig.cyclopsBiteStrength.get().doubleValue());
    }
    
    @Inject(
        method = "doHurtTarget(Lnet/minecraft/world/entity/Entity;)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onDoHurtTarget(Entity entityIn, CallbackInfoReturnable<Boolean> cir) {
        EntityCyclops cyclops = (EntityCyclops) (Object) this;

        boolean canStomp = CyclopsConfig.cyclopsCanStompAttack.get();
        boolean canGrab = CyclopsConfig.cyclopsCanGrabAttack.get();
        boolean canKick = CyclopsConfig.cyclopsCanKickAttack.get();
        
        if (!canStomp && !canGrab && !canKick) {
            cir.setReturnValue(false);
            return;
        }
        
        int attackDecision = cyclops.getRandom().nextInt(3);
        
        boolean attackPerformed = tryPerformAttack(cyclops, entityIn, attackDecision, canStomp, canGrab, canKick);

        if (!attackPerformed) {
            for (int i = 0; i < 3 && !attackPerformed; i++) {
                if (i != attackDecision) {
                    attackPerformed = tryPerformAttack(cyclops, entityIn, i, canStomp, canGrab, canKick);
                }
            }
        }

        cir.setReturnValue(attackPerformed);
    }

    private boolean tryPerformAttack(EntityCyclops cyclops, Entity target, int attackType, boolean canStomp, boolean canGrab, boolean canKick) {
        switch (attackType) {
            case 0:
                if (canStomp) {
                    cyclops.setAnimation(EntityCyclops.ANIMATION_STOMP);
                    return true;
                }
                break;
                
            case 1:
                if (canGrab) {
                    if (!target.hasPassenger(cyclops)
                        && target.getBbWidth() < 1.95F
                        && !(target instanceof com.github.alexthe666.iceandfire.entity.EntityDragonBase)
                        && !target.getType().is((ForgeRegistries.ENTITY_TYPES.tags().createTagKey(com.github.alexthe666.iceandfire.misc.IafTagRegistry.CYCLOPS_UNLIFTABLES)))) {
                        cyclops.setAnimation(EntityCyclops.ANIMATION_EATPLAYER);
                        target.stopRiding();
                        target.startRiding(cyclops, true);
                        return true;
                    } else {
                        if (canStomp) {
                            cyclops.setAnimation(EntityCyclops.ANIMATION_STOMP);
                            return true;
                        }
                    }
                }
                break;
                
            case 2:
                if (canKick) {
                    cyclops.setAnimation(EntityCyclops.ANIMATION_KICK);
                    return true;
                }
                break;
        }
        
        return false;
    }
}