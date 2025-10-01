package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;
import com.xiaoyu.suixingxiugai.util.twilightforest.entity.LichTeleportUtil;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;

import twilightforest.entity.boss.Lich;
import twilightforest.entity.boss.IBossLootBuffer;
import twilightforest.init.TFSounds;

@Mixin(Lich.class)
public abstract class LichMixin {
    
    @Shadow
    private ServerBossEvent bossInfo;

    @Unique
    private DamageSource currentDamageSource;
    
    @Accessor("SHIELD_STRENGTH")
    public static EntityDataAccessor<Integer> getShieldStrengthAccessor() {
        throw new AssertionError();
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyAttributes(CallbackInfo ci) {
        Lich lich = (Lich) (Object) this;

        AttributeInstance maxHealthAttribute = lich.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            maxHealthAttribute.setBaseValue(LichConfig.lichMaxHealth.get());
            if (lich.getHealth() > LichConfig.lichMaxHealth.get()) {
                lich.setHealth((float) LichConfig.lichMaxHealth.get().doubleValue());
            }
        }

        AttributeInstance movementSpeedAttribute = lich.getAttribute(Attributes.MOVEMENT_SPEED);
        if (movementSpeedAttribute != null) {
            movementSpeedAttribute.setBaseValue(LichConfig.lichMovementSpeed.get());
        }

        AttributeInstance attackDamageAttribute = lich.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageAttribute != null) {
            attackDamageAttribute.setBaseValue(LichConfig.lichAttackDamage.get());
        }
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 217))
    private int modifyXpReward(int original) {
        return LichConfig.lichXpReward.get();
    }

    @Inject(
        method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", 
        at = @At("HEAD")
    )
    private void suixingxiugai$storeDamageSource(DamageSource src, float damage, CallbackInfoReturnable<Boolean> cir) {
        this.currentDamageSource = src;
    }

    @Inject(
        method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/Lich;getShieldStrength()I", 
            ordinal = 0
        ), 
        cancellable = true
    )
    private void suixingxiugai$handlePhysicalDamageShield(DamageSource src, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (!LichConfig.enableLichShieldModification.get()) {
            return;
        }
        
        Lich lich = (Lich) (Object) this;
        if (lich.getShieldStrength() > 0 && isEntityCausedDamage(src)) {
            lich.setShieldStrength(lich.getShieldStrength() - 1);
            lich.playSound(TFSounds.SHIELD_BREAK.get(), 1.0F, lich.getVoicePitch() * 2.0F);
            lich.gameEvent(GameEvent.ENTITY_DAMAGE);
            cir.cancel();
            cir.setReturnValue(false);
        }
    }

    private boolean isEntityCausedDamage(DamageSource source) {
        Entity directEntity = source.getDirectEntity();
        Entity entity = source.getEntity();
        return directEntity != null || entity != null;
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void suixingxiugai$modifyInitialShieldStrength(CallbackInfo ci) {
        Lich lich = (Lich) (Object) this;
        SynchedEntityData entityData = lich.getEntityData();
        entityData.set(getShieldStrengthAccessor(), LichConfig.lichShieldStrength.get());
    }
    
    @Inject(
        method = "aiStep", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/server/level/ServerBossEvent;setProgress(F)V", 
            ordinal = 0, 
            shift = At.Shift.AFTER
        )
    )
    private void suixingxiugai$modifyShieldPhaseBossBar(CallbackInfo ci) {
        Lich lich = (Lich) (Object) this;
        if (!lich.level().isClientSide() && lich.getPhase() == 1) {
            int maxShield = LichConfig.lichShieldStrength.get();
            float progress = (float) lich.getShieldStrength() / (float) maxShield;
            this.bossInfo.setProgress(Math.max(0.0F, Math.min(1.0F, progress)));

            if (maxShield > 6 && this.bossInfo.getOverlay() == BossEvent.BossBarOverlay.NOTCHED_6) {
                this.bossInfo.setOverlay(BossEvent.BossBarOverlay.PROGRESS);
            }
        }
    }

    @Redirect(
        method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", 
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/Lich;isShadowClone()Z"
        )
    )
    private boolean suixingxiugai$redirectIsShadowClone(Lich lich) {
        boolean isClone = lich.isShadowClone();

        if (!isClone) {
            return false;
        }

        if (this.currentDamageSource != null && this.currentDamageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }

        if (LichConfig.lichPhantomCanBeAttacked.get()) {
            return false;
        }
        
        return true;
    }

    @Redirect(
        method = "remove(Lnet/minecraft/world/entity/Entity$RemovalReason;)V",
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/IBossLootBuffer;depositDropsIntoChest(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/server/level/ServerLevel;)V"
        )
    )
    private void suixingxiugai$controlPhantomChestDrop(LivingEntity lich, BlockState chest, BlockPos pos, ServerLevel serverLevel) {
        if (lich instanceof Lich l) {
            if (l.isShadowClone()) {
                if (LichConfig.lichPhantomDropChest.get()) {
                    IBossLootBuffer.depositDropsIntoChest(l, chest, pos, serverLevel);
                }
            } else {
                IBossLootBuffer.depositDropsIntoChest(l, chest, pos, serverLevel);
            }
        }
    }

    @Inject(
        method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z",
        at = @At(
            value = "INVOKE", 
            target = "Ltwilightforest/entity/boss/Lich;teleportToSightOfEntity(Lnet/minecraft/world/entity/Entity;)V"
        ),
        cancellable = true
    )
    private void suixingxiugai$controlLichHurtTeleport(DamageSource src, float damage, CallbackInfoReturnable<Boolean> cir) {
        Lich lich = (Lich) (Object) this;
        LichTeleportUtil.checkAndCancelTeleport(lich, cir);
    }
}