package com.xiaoyu.suixingxiugai.config.iceandfire.entity;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class DeathWormConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> deathWormPassableBlocks;

    static {
        BUILDER.push("Death Worm Config (死亡蠕虫配置)");
        
        deathWormPassableBlocks = BUILDER
                .comment("死亡蠕虫可以穿过的方块列表 (List of blocks that death worms can pass through)")
                .translation("deathWormPassableBlocks")
                .defineListAllowEmpty(List.of("deathWormPassableBlocks"), 
                        () -> List.of("minecraft:sand", "minecraft:red_sand"), 
                        obj -> obj instanceof String);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}