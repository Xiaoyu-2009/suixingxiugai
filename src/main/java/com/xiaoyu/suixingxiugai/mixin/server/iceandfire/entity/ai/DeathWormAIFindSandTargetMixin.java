package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity.ai;

import com.xiaoyu.suixingxiugai.util.iceandfire.entity.DeathWormBlockMatcher;
import com.github.alexthe666.iceandfire.entity.ai.DeathWormAIFindSandTarget;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = DeathWormAIFindSandTarget.class, priority = 1100)
public class DeathWormAIFindSandTargetMixin {
    
    @Redirect(
        method = "findSandTarget()Lnet/minecraft/core/BlockPos;",
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z"
        )
    )
    private boolean redirectIsSand(BlockState state, TagKey<Block> tag) {
        return DeathWormBlockMatcher.canPassThrough(state);
    }
}