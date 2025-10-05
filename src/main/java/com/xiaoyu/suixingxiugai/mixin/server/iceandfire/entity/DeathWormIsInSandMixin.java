package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.xiaoyu.suixingxiugai.util.iceandfire.entity.DeathWormBlockMatcher;
import com.github.alexthe666.iceandfire.entity.EntityDeathWorm;

import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EntityDeathWorm.class, priority = 1100)
public class DeathWormIsInSandMixin {
    
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
}