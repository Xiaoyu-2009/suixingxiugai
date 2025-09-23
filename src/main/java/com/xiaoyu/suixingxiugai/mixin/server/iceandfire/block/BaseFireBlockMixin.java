package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.block;

import com.xiaoyu.suixingxiugai.util.BlockOwnerTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseFireBlock.class)
public class BaseFireBlockMixin {
    
    @Inject(
        method = "entityInside(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;)V",
        at = @At("HEAD"), 
        cancellable = true
    )
    private void onEntityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn, CallbackInfo ci) {
        if (entityIn instanceof Player) {
            Player player = (Player) entityIn;
            if (BlockOwnerTracker.isBlockOwner(pos, player)) {
                ci.cancel();
                return;
            }
        }
    }
}