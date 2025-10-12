package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.item.wand;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.ZombieWandConfig;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import twilightforest.item.ZombieWandItem;

import javax.annotation.Nonnull;

@Mixin(ZombieWandItem.class)
public class ZombieWandItemMixin {

    @Inject(
        method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;", 
        at = @At("TAIL")
    )
    private void onUse(Level level, Player player, @Nonnull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getDamageValue() >= ZombieWandConfig.zombieWandUses.get()) {
            cir.setReturnValue(InteractionResultHolder.fail(stack));
            return;
        }

        if (!player.isCreative()) {
            player.getCooldowns().addCooldown(stack.getItem(), ZombieWandConfig.zombieWandCooldown.get());
        }
    }

    public int getMaxDamage(ItemStack stack) {
        return ZombieWandConfig.zombieWandUses.get();
    }
}