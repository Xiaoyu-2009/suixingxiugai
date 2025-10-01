package com.xiaoyu.suixingxiugai.config.curios.iceandfire;

import net.minecraftforge.common.ForgeConfigSpec;

public class HydraHeartConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue hydraHeartWorkAsCurio;

    static {
        BUILDER.push("Hydra Heart Config (九头蛇心脏配置)");
        
        hydraHeartWorkAsCurio = BUILDER
                .comment("九头蛇心脏是否作用在饰品 (Whether Hydra Heart effects work when worn as curio)")
                .define("hydraHeartWorkAsCurio", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}