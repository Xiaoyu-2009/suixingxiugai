package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.item.wand;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import twilightforest.capabilities.CapabilityList;
import twilightforest.capabilities.shield.IShieldCapability;
import twilightforest.item.FortificationWandItem;
import twilightforest.network.TFPacketHandler;
import twilightforest.network.UpdateShieldPacket;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

@Mixin(FortificationWandItem.class)
public class FortificationWandItemMixin {

    @Inject(
        method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private void onUse(Level level, Player player, @Nonnull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getDamageValue() >= WandConfig.fortificationWandUses.get()) {
            cir.setReturnValue(InteractionResultHolder.fail(stack));
            return;
        }

        if (!level.isClientSide()) {
            Entity targetEntity = WandConfig.enableFortificationWandTargeting.get() ? getEntityAtCursor(player, 20.0D) : null;
            Entity target = (targetEntity != null) ? targetEntity : player;
            LazyOptional<IShieldCapability> shieldCapability = target.getCapability(CapabilityList.SHIELDS);
            
            if (shieldCapability.isPresent()) {
                shieldCapability.ifPresent(cap -> {
                    cap.replenishShields();
                    cap.setShields(WandConfig.fortificationWandShieldAmount.get(), true);
                    sendShieldUpdatePacket(target, cap);
                });

                if (!player.isCreative()) {
                    stack.hurt(1, level.getRandom(), null);
                }
            }
        }

        if (!player.isCreative()) {
            player.getCooldowns().addCooldown(stack.getItem(), WandConfig.fortificationWandCooldown.get());
        }

        cir.setReturnValue(InteractionResultHolder.success(stack));
    }

    public int getMaxDamage(ItemStack stack) {
        return WandConfig.fortificationWandUses.get();
    }

    private static void sendShieldUpdatePacket(Entity entity, IShieldCapability cap) {
        TFPacketHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new UpdateShieldPacket(entity, cap));
    }

    private static Entity getEntityAtCursor(Player player, double range) {
        Vec3 srcVec = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getViewVector(1.0F);
        Vec3 destVec = srcVec.add(lookVec.x * range, lookVec.y * range, lookVec.z * range);
        float boundingBoxExpansion = 1.0F;
        List<Entity> possibleList = player.level().getEntities(player, player.getBoundingBox().expandTowards(lookVec.x * range, lookVec.y * range, lookVec.z * range).inflate(boundingBoxExpansion, boundingBoxExpansion, boundingBoxExpansion));
        double closestDistance = 0;
        Entity pointedEntity = null;

        for (Entity possibleEntity : possibleList) {
            if (possibleEntity.isPickable() && possibleEntity != player) {
                float borderSize = possibleEntity.getPickRadius();
                AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
                Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < closestDistance || closestDistance == 0.0D) {
                        pointedEntity = possibleEntity;
                        closestDistance = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double distance = srcVec.distanceToSqr(interceptPos.get());
                    if (distance < closestDistance || closestDistance == 0.0D) {
                        pointedEntity = possibleEntity;
                        closestDistance = distance;
                    }
                }
            }
        }
        
        return pointedEntity;
    }
}