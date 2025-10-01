package com.xiaoyu.suixingxiugai.config.curios.iceandfire;

import net.minecraftforge.common.ForgeConfigSpec;

public class CyclopsEyeConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue cyclopsEyeWorkAsCurio;

    static {
        BUILDER.push("Cyclops Eye Config (巨人独眼配置)");
        
        cyclopsEyeWorkAsCurio = BUILDER
                .comment("巨人独眼是否作用在饰品 (Whether Cyclops Eye effects work when worn as curio)")
                .define("cyclopsEyeWorkAsCurio", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}