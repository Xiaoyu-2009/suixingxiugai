package com.xiaoyu.suixingxiugai.config.iceandfire;

import net.minecraftforge.common.ForgeConfigSpec;

public class IceandfireConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue enableStatueRestoreCommand;

    static {
        BUILDER.push("IceAndFire (冰火传说)");
        
        enableStatueRestoreCommand = BUILDER
                .comment("启用石像恢复指令 (Enable statue restore command)")
                .define("enableStatueRestoreCommand", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}