package com.xiaoyu.suixingxiugai.config.iceandfire.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class SirenConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue sirenCanSing;

    static {
        BUILDER.push("Siren Config (塞壬配置)");
        
        sirenCanSing = BUILDER
                .comment("塞壬是否能唱歌 (Whether siren can sing)")
                .define("sirenCanSing", true);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}