package com.xiaoyu.suixingxiugai.config.iceandfire.entity.dragon;

import net.minecraftforge.common.ForgeConfigSpec;

public class DragonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue enableDragonResurrectionCommand;

    static {
        BUILDER.push("IceAndFire (冰火传说)");
        BUILDER.push("Dragon Config (龙配置)");
        
        enableDragonResurrectionCommand = BUILDER
                .comment("启用龙复活指令 (Enable dragon resurrection command)")
                .define("enableDragonResurrectionCommand", true);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}