package com.xiaoyu.suixingxiugai.network;

import com.xiaoyu.suixingxiugai.client.ClientEvents;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DamageDisplayMessage {
    private final int entityId;
    private final float damageAmount;
    private final ResourceLocation damageType;
    private final boolean isCrit;

    public DamageDisplayMessage(int entityId, float damageAmount, ResourceLocation damageType, boolean isCrit) {
        this.entityId = entityId;
        this.damageAmount = damageAmount;
        this.damageType = damageType;
        this.isCrit = isCrit;
    }

    public DamageDisplayMessage(LivingEntity entity, float damageAmount, DamageSource source, boolean isCrit) {
        this(entity.getId(), damageAmount, source.typeHolder().unwrapKey().get().location(), isCrit);
    }

    public static void encode(DamageDisplayMessage msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeFloat(msg.damageAmount);
        buf.writeResourceLocation(msg.damageType);
        buf.writeBoolean(msg.isCrit);
    }

    public static DamageDisplayMessage decode(FriendlyByteBuf buf) {
        return new DamageDisplayMessage(
            buf.readInt(),
            buf.readFloat(),
            buf.readResourceLocation(),
            buf.readBoolean()
        );
    }

    public static void handle(DamageDisplayMessage msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getDirection().getReceptionSide().isClient()) {
                net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getInstance();
                if (mc.level != null) {
                    Entity entity = mc.level.getEntity(msg.entityId);
                    if (entity != null) {
                        entity.level().addParticle(
                            ClientEvents.DAMAGE_NUMBER.get(),
                            entity.getX(),
                            entity.getY() + entity.getBbHeight() / 2,
                            entity.getZ(),
                            msg.damageAmount,
                            0,
                            msg.isCrit ? 1.0 : 0.0
                        );
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

    public int getEntityId() {
        return entityId;
    }

    public float getDamageAmount() {
        return damageAmount;
    }

    public ResourceLocation getDamageType() {
        return damageType;
    }

    public boolean isCrit() {
        return isCrit;
    }
}