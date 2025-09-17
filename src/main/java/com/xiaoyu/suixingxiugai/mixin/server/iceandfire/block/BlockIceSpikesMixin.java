package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.block;

import com.github.alexthe666.iceandfire.block.BlockIceSpikes;
import com.xiaoyu.suixingxiugai.util.BlockOwnerTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockIceSpikes.class)
public class BlockIceSpikesMixin {
    @Inject(method = "stepOn(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/Entity;)V",
            at = @At("HEAD"), cancellable = true)
    private void onStepOn(Level worldIn, BlockPos pos, BlockState pState, Entity entityIn, CallbackInfo ci) {
        if (entityIn instanceof Player) {
            Player player = (Player) entityIn;
            if (BlockOwnerTracker.isBlockOwner(pos, player)) {
                ci.cancel();
                return;
            }
        }
    }
}