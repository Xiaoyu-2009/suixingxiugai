package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.Shadow;
import twilightforest.entity.boss.Lich;
import twilightforest.init.TFSounds;
import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;

@Mixin(Lich.class)
public abstract class LichMixin {
    
    @Shadow
    private ServerBossEvent bossInfo;
    
    @Accessor("SHIELD_STRENGTH")
    public static EntityDataAccessor<Integer> getShieldStrengthAccessor() {
        throw new AssertionError();
    }

    @Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", 
    at = @At(value = "INVOKE", target = "Ltwilightforest/entity/boss/Lich;getShieldStrength()I", ordinal = 0), cancellable = true)
    private void suixingxiugai$handlePhysicalDamageShield(DamageSource src, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (!SuixingxiugaiConfig.enableLichShieldModification.get()) {
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
        entityData.set(getShieldStrengthAccessor(), SuixingxiugaiConfig.lichShieldStrength.get());
    }
    
    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerBossEvent;setProgress(F)V", ordinal = 0), cancellable = true)
    private void suixingxiugai$modifyShieldPhaseBossBar(CallbackInfo ci) {
        Lich lich = (Lich) (Object) this;
        if (!lich.level().isClientSide() && lich.getPhase() == 1) {
            if (SuixingxiugaiConfig.enableLichShieldModification.get()) {
                int maxShield = SuixingxiugaiConfig.lichShieldStrength.get();

                float progress = (float) lich.getShieldStrength() / (float) maxShield;
                this.bossInfo.setProgress(Math.max(0.0F, Math.min(1.0F, progress)));

                if (maxShield > 6 && this.bossInfo.getOverlay() == BossEvent.BossBarOverlay.NOTCHED_6) {
                    this.bossInfo.setOverlay(BossEvent.BossBarOverlay.PROGRESS);
                }

                ci.cancel();
            }
        }
    }
}