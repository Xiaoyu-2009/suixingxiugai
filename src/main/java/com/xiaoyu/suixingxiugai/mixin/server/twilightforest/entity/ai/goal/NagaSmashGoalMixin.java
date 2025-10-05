package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.NagaConfig;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import twilightforest.entity.boss.Naga;
import twilightforest.entity.ai.goal.NagaSmashGoal;

@Mixin(NagaSmashGoal.class)
public class NagaSmashGoalMixin {

    @Redirect(
        method = "start",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z",
            ordinal = 0
        )
    )
    private boolean redirectLeafBlockCheck(BlockState state, TagKey<Block> tagKey) {
        if (!NagaConfig.nagaCanDestroyBlocks.get()) {
            return false;
        }
        return state.is(tagKey);
    }

    @Redirect(
        method = "start",
        at = @At(
            value = "INVOKE",
            target = "Ltwilightforest/entity/boss/Naga;shouldDestroyAllBlocks()Z"
        )
    )
    private boolean redirectShouldDestroyAllBlocks(Naga naga) {
        if (!NagaConfig.nagaCanDestroyBlocks.get()) {
            return false;
        }
        return naga.shouldDestroyAllBlocks();
    }
}