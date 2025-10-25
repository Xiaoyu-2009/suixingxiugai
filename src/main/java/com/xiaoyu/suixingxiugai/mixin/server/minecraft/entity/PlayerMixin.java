package com.xiaoyu.suixingxiugai.mixin.server.minecraft.entity;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.xiaoyu.suixingxiugai.network.DamageDisplayMessage;
import com.xiaoyu.suixingxiugai.network.NetworkHandler;
import com.xiaoyu.suixingxiugai.event.CriticalHitEventHandler;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {
    
    @Inject(method = "actuallyHurt", at = @At("TAIL"))
    private void onPlayerActuallyHurt(DamageSource damageSrc, float damageAmount, CallbackInfo ci) {
        if (!SuixingxiugaiConfig.enableDamageNumberDisplay.get()) {
            return;
        }

        Player player = (Player) (Object) this;

        if (player.level().isClientSide || damageAmount == 0) {
            return;
        }

        boolean isCrit = CriticalHitEventHandler.isLastAttackCritical(player);
        DamageDisplayMessage message = new DamageDisplayMessage(player, damageAmount, damageSrc, isCrit);

        if (player instanceof ServerPlayer) {
            NetworkHandler.sendToPlayer((ServerPlayer) player, message);
        }

        if (damageSrc.getEntity() instanceof ServerPlayer && 
            damageSrc.is(DamageTypes.PLAYER_ATTACK)
        ) {
            ServerPlayer attacker = (ServerPlayer) damageSrc.getEntity();
            if (attacker != player) {
                NetworkHandler.sendToPlayer(attacker, message);
            }
        }
    }
}