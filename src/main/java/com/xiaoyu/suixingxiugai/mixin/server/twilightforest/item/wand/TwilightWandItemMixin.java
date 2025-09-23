package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.item.wand;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twilightforest.item.TwilightWandItem;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.WandConfig;

import javax.annotation.Nonnull;

@Mixin(TwilightWandItem.class)
public class TwilightWandItemMixin {

    @Inject(
        method = "use(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResultHolder;", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private void onUse(Level level, Player player, @Nonnull InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getDamageValue() >= WandConfig.twilightWandUses.get()) {
            cir.setReturnValue(InteractionResultHolder.fail(stack));
        }
    }

    public int getMaxDamage(ItemStack stack) {
        return WandConfig.twilightWandUses.get();
    }
}