package com.xiaoyu.suixingxiugai.config.iceandfire.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class GorgonHeadConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue gorgonHeadUses;
    public static final ForgeConfigSpec.BooleanValue gorgonHeadPlayBreakSound;

    static {
        BUILDER.push("Gorgon Head Configuration (蛇发女妖头颅配置)");
        
        gorgonHeadUses = BUILDER
                .comment("蛇发女妖头颅使用次数 (Gorgon head uses)")
                .defineInRange("gorgonHeadUses", 1, 1, Integer.MAX_VALUE);
                
        gorgonHeadPlayBreakSound = BUILDER
                .comment("是否播放蛇发女妖头颅碎裂音效 (Whether to play gorgon head break sound)")
                .define("gorgonHeadPlayBreakSound", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}