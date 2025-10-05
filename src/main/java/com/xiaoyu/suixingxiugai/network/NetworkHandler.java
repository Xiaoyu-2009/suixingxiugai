package com.xiaoyu.suixingxiugai.network;

import com.xiaoyu.suixingxiugai.SuixingXiugai;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.PacketDistributor;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(SuixingXiugai.MOD_ID, "damage_display"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );
    
    public static void registerMessages() {
        CHANNEL.registerMessage(
            0, DamageDisplayMessage.class, DamageDisplayMessage::encode, 
            DamageDisplayMessage::decode, DamageDisplayMessage::handle
        );
    }
    
    public static void sendToAllTracking(Entity entity, DamageDisplayMessage message) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
    }
    
    public static void sendToPlayer(ServerPlayer player, DamageDisplayMessage message) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}