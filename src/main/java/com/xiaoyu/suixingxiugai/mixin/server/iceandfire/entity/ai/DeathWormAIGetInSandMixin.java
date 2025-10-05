package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity.ai;

import com.xiaoyu.suixingxiugai.util.iceandfire.entity.DeathWormBlockMatcher;
import com.github.alexthe666.iceandfire.entity.ai.DeathWormAIGetInSand;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = DeathWormAIGetInSand.class, priority = 1100)
public class DeathWormAIGetInSandMixin {
    
    @Redirect(
        method = "findPossibleShelter()Lnet/minecraft/world/phys/Vec3;",
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z"
        )
    )
    private boolean redirectIsSand(BlockState state, TagKey<Block> tag) {
        return DeathWormBlockMatcher.canPassThrough(state);
    }
}