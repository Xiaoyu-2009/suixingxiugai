package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.item.wand;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.LifedrainScepterConfig;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import twilightforest.item.LifedrainScepterItem;

import javax.annotation.Nonnull;

@Mixin(LifedrainScepterItem.class)
public class LifedrainScepterItemMixin {

    @Inject(
        method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private void onUse(Level level, Player player, @Nonnull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getDamageValue() >= LifedrainScepterConfig.lifedrainScepterUses.get()) {
            cir.setReturnValue(InteractionResultHolder.fail(stack));
        }
    }
    
    @Inject(
        method = "onUseTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;I)V", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private void onUseTick(Level level, LivingEntity living, ItemStack stack, int count, CallbackInfo ci) {
        if (stack.getDamageValue() >= LifedrainScepterConfig.lifedrainScepterUses.get()) {
            living.stopUsingItem();
            ci.cancel();
        }
    }
    
    @Inject(
        method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;", 
        at = @At("TAIL")
    )
    private void onUseTail(Level level, Player player, @Nonnull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (!player.isCreative() && stack.getDamageValue() < LifedrainScepterConfig.lifedrainScepterUses.get()) {
            player.getCooldowns().addCooldown(stack.getItem(), LifedrainScepterConfig.lifedrainScepterCooldown.get());
        }
    }

    public int getMaxDamage(ItemStack stack) {
        return LifedrainScepterConfig.lifedrainScepterUses.get();
    }
}