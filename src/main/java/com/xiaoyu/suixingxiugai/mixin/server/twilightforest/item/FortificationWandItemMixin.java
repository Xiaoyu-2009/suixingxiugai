package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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
import twilightforest.item.FortificationWandItem;
import twilightforest.capabilities.CapabilityList;
import twilightforest.capabilities.shield.IShieldCapability;
import twilightforest.network.TFPacketHandler;
import twilightforest.network.UpdateShieldPacket;
import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

@Mixin(FortificationWandItem.class)
public class FortificationWandItemMixin {

    @Inject(method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;", 
            at = @At("HEAD"), cancellable = true)
    private void onUse(Level level, Player player, @Nonnull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (!SuixingxiugaiConfig.enableFortificationWandTargeting.get()) {
            return;
        }
        
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getDamageValue() >= SuixingxiugaiConfig.fortificationWandUses.get()) {
            cir.setReturnValue(InteractionResultHolder.fail(stack));
            return;
        }

        if (!level.isClientSide()) {
            Entity targetEntity = getEntityAtCursor(player, 20.0D);
            
            if (targetEntity != null) {
                LazyOptional<IShieldCapability> shieldCapability = targetEntity.getCapability(CapabilityList.SHIELDS);
                if (shieldCapability.isPresent()) {
                    shieldCapability.ifPresent(cap -> {
                        cap.setShields(SuixingxiugaiConfig.fortificationWandShieldAmount.get(), true);
                        sendShieldUpdatePacket(targetEntity, cap);
                    });

                    if (!player.isCreative()) {
                        stack.hurt(1, level.getRandom(), null);
                    }
                }
            } else {
                LazyOptional<IShieldCapability> shieldCapability = player.getCapability(CapabilityList.SHIELDS);
                if (shieldCapability.isPresent()) {
                    shieldCapability.ifPresent(cap -> {
                        cap.setShields(SuixingxiugaiConfig.fortificationWandShieldAmount.get(), true);
                        sendShieldUpdatePacket(player, cap);
                    });

                    if (!player.isCreative()) {
                        stack.hurt(1, level.getRandom(), null);
                    }
                }
            }
        }

        if (!player.isCreative()) {
            player.getCooldowns().addCooldown(stack.getItem(), 1200);
        }

        cir.setReturnValue(InteractionResultHolder.success(stack));
    }

    public int getMaxDamage(ItemStack stack) {
        return SuixingxiugaiConfig.fortificationWandUses.get();
    }

    private static void sendShieldUpdatePacket(Entity entity, IShieldCapability cap) {
        TFPacketHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new UpdateShieldPacket(entity, cap));
    }

    private static Entity getEntityAtCursor(Player player, double range) {
        Vec3 srcVec = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getViewVector(1.0F);
        Vec3 destVec = srcVec.add(lookVec.x * range, lookVec.y * range, lookVec.z * range);
        float var9 = 1.0F;
        List<Entity> possibleList = player.level().getEntities(player, player.getBoundingBox().expandTowards(lookVec.x * range, lookVec.y * range, lookVec.z * range).inflate(var9, var9, var9));
        double hitDist = 0;
        Entity pointedEntity = null;

        for (Entity possibleEntity : possibleList) {
            if (possibleEntity.isPickable() && possibleEntity != player) {
                float borderSize = possibleEntity.getPickRadius();
                AABB collisionBB = possibleEntity.getBoundingBox().inflate(borderSize, borderSize, borderSize);
                Optional<Vec3> interceptPos = collisionBB.clip(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double hitDistTemp = srcVec.distanceToSqr(interceptPos.get());
                    if (hitDistTemp < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = hitDistTemp;
                    }
                }
            }
        }
        
        return pointedEntity;
    }
}