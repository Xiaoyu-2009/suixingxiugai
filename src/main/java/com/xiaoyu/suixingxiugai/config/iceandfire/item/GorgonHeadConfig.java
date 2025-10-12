package com.xiaoyu.suixingxiugai.config.iceandfire.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class GorgonHeadConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue gorgonHeadUses;
    public static final ForgeConfigSpec.BooleanValue gorgonHeadPlayBreakSound;
    public static final ForgeConfigSpec.BooleanValue gorgonHeadMustPetrifyToConsume;
    public static final ForgeConfigSpec.BooleanValue gorgonHeadCanPetrifyAllEntities;

    static {
        BUILDER.push("Gorgon Head Configuration (蛇发女妖头颅配置)");
        
        gorgonHeadUses = BUILDER
                .comment("蛇发女妖头颅使用次数 (Gorgon head uses)")
                .defineInRange("gorgonHeadUses", 1, 1, Integer.MAX_VALUE);
                
        gorgonHeadPlayBreakSound = BUILDER
                .comment("是否播放蛇发女妖头颅碎裂音效 (Whether to play gorgon head break sound)")
                .define("gorgonHeadPlayBreakSound", true);
                
        gorgonHeadMustPetrifyToConsume = BUILDER
                .comment("蛇发女妖头颅是否必须石化目标实体才减少物品 (Whether the gorgon head must petrify the target entity to consume the item)")
                .define("gorgonHeadMustPetrifyToConsume", true);
                
        gorgonHeadCanPetrifyAllEntities = BUILDER
                .comment("蛇发女妖头颅是否能石化一切实体 (Whether the gorgon head can petrify all entities)")
                .define("gorgonHeadCanPetrifyAllEntities", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}