package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.xiaoyu.suixingxiugai.util.iceandfire.entity.DeathWormBlockMatcher;
import com.github.alexthe666.iceandfire.entity.EntityDeathWorm;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EntityDeathWorm.class, priority = 1100)
public class EntityDeathWormMixin {
    
    @Redirect(
        method = "isInSandStrict()Z",
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z"
        )
    )
    private boolean redirectIsSand(BlockState state, net.minecraft.tags.TagKey<net.minecraft.world.level.block.Block> tag) {
        return DeathWormBlockMatcher.canPassThrough(state);
    }
    
    @Inject(
        method = "canPassThrough(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/phys/shapes/VoxelShape;)Z", 
        at = @At("HEAD"), 
        cancellable = true, 
        remap = false
    )
    private void onCanPassThrough(BlockPos pos, BlockState state, VoxelShape shape, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(DeathWormBlockMatcher.canPassThrough(state));
    }
}