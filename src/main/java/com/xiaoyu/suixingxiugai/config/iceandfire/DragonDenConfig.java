package com.xiaoyu.suixingxiugai.config.iceandfire;

import net.minecraftforge.common.ForgeConfigSpec;

public class DragonDenConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue undergroundDragonDenMinYLevel;
    public static final ForgeConfigSpec.IntValue undergroundDragonDenMaxYLevel;
    public static final ForgeConfigSpec.BooleanValue undergroundDragonDenCanGenerateInMidAir;

    static {
        BUILDER.push("Dragon Den Config (龙巢配置)");

        undergroundDragonDenMinYLevel = BUILDER
                .comment("地下龙巢生成Y轴最低高度 (Underground dragon den generation minimum Y level)")
                .defineInRange("undergroundDragonDenMinYLevel", -50, Integer.MIN_VALUE, Integer.MAX_VALUE);
                
        undergroundDragonDenMaxYLevel = BUILDER
                .comment("地下龙巢生成Y轴最大高度 (Underground dragon den generation maximum Y level)")
                .defineInRange("undergroundDragonDenMaxYLevel", 10, Integer.MIN_VALUE, Integer.MAX_VALUE);

        undergroundDragonDenCanGenerateInMidAir = BUILDER
                .comment("地下龙巢是否可以凭空生成 (Whether underground dragon dens can generate in mid-air)")
                .define("undergroundDragonDenCanGenerateInMidAir", false);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}