package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.item.wand;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

@Mixin(targets = "twilightforest.item.FortificationWandItem")
public class FortificationWandItemMixin {

    @Inject(
        method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private void onUse(Level level, Player player, @Nonnull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        
        if (!WandConfig.enableFortificationWandTargeting.get()) {
            return;
        }
        
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getDamageValue() >= WandConfig.fortificationWandUses.get()) {
            cir.setReturnValue(InteractionResultHolder.fail(stack));
            return;
        }

        if (!level.isClientSide()) {
            Entity targetEntity = getEntityAtCursor(player, 20.0D);
            
            if (targetEntity != null) {
                try {
                    Class<?> capabilityListClass = Class.forName("twilightforest.capabilities.CapabilityList");
                    Object shieldsCapability = capabilityListClass.getDeclaredField("SHIELDS").get(null);
                    
                    LazyOptional<?> shieldCapability = (LazyOptional<?>) targetEntity.getClass()
                        .getMethod("getCapability", Object.class)
                        .invoke(targetEntity, shieldsCapability);
                    
                    if ((boolean) shieldCapability.getClass().getMethod("isPresent").invoke(shieldCapability)) {
                        shieldCapability.getClass().getMethod("ifPresent", java.util.function.Consumer.class)
                            .invoke(shieldCapability, (java.util.function.Consumer<?>) cap -> {
                                try {
                                    cap.getClass().getMethod("replenishShields").invoke(cap);
                                    cap.getClass().getMethod("setShields", int.class, boolean.class)
                                        .invoke(cap, WandConfig.fortificationWandShieldAmount.get(), true);
                                    sendShieldUpdatePacket(targetEntity, cap);
                                } catch (Exception e) {}
                            });
                    }
                } catch (Exception e) {}

                if (!player.isCreative()) {
                    try {
                        stack.getClass().getMethod("hurt", int.class, java.util.Random.class, Object.class)
                            .invoke(stack, 1, level.getRandom(), null);
                    } catch (Exception e) {}
                }
            } else {
                try {
                    Class<?> capabilityListClass = Class.forName("twilightforest.capabilities.CapabilityList");
                    Object shieldsCapability = capabilityListClass.getDeclaredField("SHIELDS").get(null);
                    
                    LazyOptional<?> shieldCapability = (LazyOptional<?>) player.getClass()
                        .getMethod("getCapability", Object.class)
                        .invoke(player, shieldsCapability);
                    
                    if ((boolean) shieldCapability.getClass().getMethod("isPresent").invoke(shieldCapability)) {
                        shieldCapability.getClass().getMethod("ifPresent", java.util.function.Consumer.class)
                            .invoke(shieldCapability, (java.util.function.Consumer<?>) cap -> {
                                try {
                                    cap.getClass().getMethod("replenishShields").invoke(cap);
                                    cap.getClass().getMethod("setShields", int.class, boolean.class)
                                        .invoke(cap, WandConfig.fortificationWandShieldAmount.get(), true);
                                    sendShieldUpdatePacket(player, cap);
                                } catch (Exception e) {}
                            });
                    }
                } catch (Exception e) {}

                if (!player.isCreative()) {
                    try {
                        stack.getClass().getMethod("hurt", int.class, java.util.Random.class, Object.class)
                            .invoke(stack, 1, level.getRandom(), null);
                    } catch (Exception e) {}
                }
            }
        }

        if (!player.isCreative()) {
            try {
                player.getClass().getMethod("getCooldowns").invoke(player);
                Object cooldowns = player.getClass().getMethod("getCooldowns").invoke(player);
                cooldowns.getClass().getMethod("addCooldown", Object.class, int.class)
                    .invoke(cooldowns, stack.getItem(), 1200);
            } catch (Exception e) {}
        }

        cir.setReturnValue(InteractionResultHolder.success(stack));
    }

    public int getMaxDamage(ItemStack stack) {
        return WandConfig.fortificationWandUses.get();
    }

    private static void sendShieldUpdatePacket(Entity entity, Object cap) {
        try {
            Class<?> tfPacketHandlerClass = Class.forName("twilightforest.network.TFPacketHandler");
            Object channel = tfPacketHandlerClass.getDeclaredField("CHANNEL").get(null);
            
            Class<?> updateShieldPacketClass = Class.forName("twilightforest.network.UpdateShieldPacket");
            Object updateShieldPacket = updateShieldPacketClass
                .getConstructor(Entity.class, Object.class)
                .newInstance(entity, cap);
            
            Class<?> packetDistributorClass = Class.forName("net.minecraftforge.network.PacketDistributor");
            Object trackingEntity = packetDistributorClass
                .getDeclaredField("TRACKING_ENTITY")
                .get(null);
            Object target = trackingEntity.getClass()
                .getMethod("with", java.util.function.Supplier.class)
                .invoke(trackingEntity, (java.util.function.Supplier<?>) () -> entity);
            
            channel.getClass()
                .getMethod("send", Object.class, Object.class)
                .invoke(channel, target, updateShieldPacket);
        } catch (Exception e) {
        }
    }

    private static Entity getEntityAtCursor(Player player, double range) {
        try {
            Vec3 srcVec = (Vec3) player.getClass().getMethod("getEyePosition", float.class).invoke(player, 1.0F);
            Vec3 lookVec = (Vec3) player.getClass().getMethod("getViewVector", float.class).invoke(player, 1.0F);
            Vec3 destVec = (Vec3) srcVec.getClass().getMethod("add", double.class, double.class, double.class)
                .invoke(srcVec, lookVec.x * range, lookVec.y * range, lookVec.z * range);
            float var9 = 1.0F;
            
            Level level = (Level) player.getClass().getMethod("level").invoke(player);
            Object boundingBox = player.getClass().getMethod("getBoundingBox").invoke(player);
            Object expandedBB = boundingBox.getClass().getMethod("expandTowards", double.class, double.class, double.class)
                .invoke(boundingBox, lookVec.x * range, lookVec.y * range, lookVec.z * range);
            Object inflatedBB = expandedBB.getClass().getMethod("inflate", double.class, double.class, double.class)
                .invoke(expandedBB, var9, var9, var9);
            
            List<Entity> possibleList = (List<Entity>) level.getClass()
                .getMethod("getEntities", Object.class, Object.class)
                .invoke(level, player, inflatedBB);
            
            double hitDist = 0;
            Entity pointedEntity = null;

            for (Entity possibleEntity : possibleList) {
                boolean isPickable = (boolean) possibleEntity.getClass().getMethod("isPickable").invoke(possibleEntity);
                if (isPickable && possibleEntity != player) {
                    float borderSize = (float) possibleEntity.getClass().getMethod("getPickRadius").invoke(possibleEntity);
                    Object entityBB = possibleEntity.getClass().getMethod("getBoundingBox").invoke(possibleEntity);
                    Object collisionBB = entityBB.getClass().getMethod("inflate", double.class, double.class, double.class)
                        .invoke(entityBB, borderSize, borderSize, borderSize);
                    
                    Optional<Vec3> interceptPos = (Optional<Vec3>) collisionBB.getClass()
                        .getMethod("clip", Vec3.class, Vec3.class)
                        .invoke(collisionBB, srcVec, destVec);

                    if ((boolean) collisionBB.getClass().getMethod("contains", Vec3.class).invoke(collisionBB, srcVec)) {
                        if (0.0D < hitDist || hitDist == 0.0D) {
                            pointedEntity = possibleEntity;
                            hitDist = 0.0D;
                        }
                    } else if (interceptPos.isPresent()) {
                        double hitDistTemp = (double) srcVec.getClass().getMethod("distanceToSqr", Vec3.class)
                            .invoke(srcVec, interceptPos.get());
                        if (hitDistTemp < hitDist || hitDist == 0.0D) {
                            pointedEntity = possibleEntity;
                            hitDist = hitDistTemp;
                        }
                    }
                }
            }
            
            return pointedEntity;
        } catch (Exception e) {
            return null;
        }
    }
}