package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.item;

import com.github.alexthe666.iceandfire.item.ItemGorgonHead;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.GorgonHeadConfig;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import javax.annotation.Nullable;

@Mixin(ItemGorgonHead.class)
public class ItemGorgonHeadMixin {

    @Unique
    private static final String USE_COUNT_TAG = "GorgonHeadUseCount";

    @Unique
    private static boolean successfullyTurnedToStone = false;
    
    @Redirect(
        method = "releaseUsing(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"
        )
    )
    private void redirectShrink(ItemStack stack, int amount, ItemStack stack2, Level worldIn, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player && player.isCreative()) {
            return;
        }
        
        int maxUses = GorgonHeadConfig.gorgonHeadUses.get();
        CompoundTag tag = stack.getOrCreateTag();
        int currentUseCount = tag.getInt(USE_COUNT_TAG);

        currentUseCount++;

        if (currentUseCount >= maxUses) {
            stack.shrink(amount);
            tag.remove(USE_COUNT_TAG);
        } else {
            tag.putInt(USE_COUNT_TAG, currentUseCount);
        }
    }
    
    @Inject(
        method = "appendHoverText(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Ljava/util/List;Lnet/minecraft/world/item/TooltipFlag;)V",
        at = @At("HEAD")
    )
    private void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag, CallbackInfo ci) {
        int maxUses = GorgonHeadConfig.gorgonHeadUses.get();

        if (maxUses > 1) {
            CompoundTag tag = stack.getOrCreateTag();
            int currentUseCount = tag.getInt(USE_COUNT_TAG);
            int remainingUses = maxUses - currentUseCount;

            tooltip.add(Component.translatable("item.suixingxiugai.gorgon_head.uses", remainingUses).withStyle(ChatFormatting.GRAY));
        }
    }

    @Inject(
        method = "releaseUsing(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"
        )
    )
    private void onEntityTurnedToStone(ItemStack stack, Level worldIn, LivingEntity entity, int timeLeft, CallbackInfo ci) {
        successfullyTurnedToStone = true;
    }

    @Inject(
        method = "releaseUsing(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)V",
        at = @At("TAIL")
    )
    private void onReleaseUsing(net.minecraft.world.item.ItemStack stack, net.minecraft.world.level.Level worldIn, net.minecraft.world.entity.LivingEntity entity, int timeLeft, CallbackInfo ci) {
        if (successfullyTurnedToStone && GorgonHeadConfig.gorgonHeadPlayBreakSound.get()) {
            if (!(entity instanceof Player player && player.isCreative())) {
                worldIn.playSound(
                    null, entity.getX(), entity.getY(), entity.getZ(),
                    SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F
                );
            }
        }

        successfullyTurnedToStone = false;
    }
}