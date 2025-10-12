package com.xiaoyu.suixingxiugai.config.iceandfire.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class DragonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue enableDragonResurrectionCommand;
    public static final ForgeConfigSpec.BooleanValue wildDragonDefaultSleepingSurface;
    public static final ForgeConfigSpec.BooleanValue wildDragonDefaultSleepingUnderground;

    static {
        BUILDER.push("Dragon Config (龙配置)");
        
        enableDragonResurrectionCommand = BUILDER
                .comment("启用龙复活指令 (Enable dragon resurrection command)")
                .define("enableDragonResurrectionCommand", true);
                
        wildDragonDefaultSleepingSurface = BUILDER
                .comment("地表生成的野生龙默认是否处于睡眠状态",
                "Whether wild dragons generated on the surface default to sleeping state")
                .define("wildDragonDefaultSleepingSurface", true);
                
        wildDragonDefaultSleepingUnderground = BUILDER
                .comment("地下龙巢生成的野生龙默认是否处于睡眠状态",
                "Whether wild dragons generated in underground dens default to sleeping state")
                .define("wildDragonDefaultSleepingUnderground", true);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}