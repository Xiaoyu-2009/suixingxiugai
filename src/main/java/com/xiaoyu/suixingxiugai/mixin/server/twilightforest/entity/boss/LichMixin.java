package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.boss;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twilightforest.entity.boss.Lich;
import twilightforest.init.TFSounds;
import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;

@Mixin(Lich.class)
public class LichMixin {
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
}