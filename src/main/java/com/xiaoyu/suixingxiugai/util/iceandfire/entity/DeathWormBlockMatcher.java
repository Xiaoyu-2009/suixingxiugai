package com.xiaoyu.suixingxiugai.util.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DeathWormConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class DeathWormBlockMatcher {

    public static boolean canPassThrough(BlockState state) {
        List<? extends String> configValue = DeathWormConfig.deathWormPassableBlocks.get();
        if (configValue != null && !configValue.isEmpty()) {
            ResourceLocation blockRegistryName = ForgeRegistries.BLOCKS.getKey(state.getBlock());
            for (String blockId : configValue) {
                try {
                    ResourceLocation configBlockId = new ResourceLocation(blockId.trim());
                    if (blockRegistryName.equals(configBlockId)) {
                        return true;
                    }
                } catch (Exception ignored) {}
            }
        }
        
        return false;
    }
}