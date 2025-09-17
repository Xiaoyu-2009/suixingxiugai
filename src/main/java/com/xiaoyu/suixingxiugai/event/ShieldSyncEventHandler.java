package com.xiaoyu.suixingxiugai.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;
import twilightforest.capabilities.CapabilityList;
import twilightforest.network.TFPacketHandler;
import twilightforest.network.UpdateShieldPacket;

public class ShieldSyncEventHandler {

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        LivingEntity living = event.getEntity();
        if (living.level().isClientSide()) {
            return;
        }

        living.getCapability(CapabilityList.SHIELDS).ifPresent(cap -> {
            if (cap.shieldsLeft() > 0) {
                int shieldsBefore = cap.shieldsLeft();

                living.level().getServer().execute(() -> {
                    int shieldsAfter = cap.shieldsLeft();
                    if (shieldsBefore != shieldsAfter) {
                        if (living instanceof ServerPlayer serverPlayer) {
                            TFPacketHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer), new UpdateShieldPacket(living, cap));
                        } else {
                            TFPacketHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> living), new UpdateShieldPacket(living, cap));
                        }
                    }
                });
            }
        });
    }
}