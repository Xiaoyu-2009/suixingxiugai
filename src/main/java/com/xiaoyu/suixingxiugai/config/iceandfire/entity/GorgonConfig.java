package com.xiaoyu.suixingxiugai.config.iceandfire.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class GorgonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue gorgonCanUsePetrify;
    public static final ForgeConfigSpec.BooleanValue gorgonCanUsePoisonFang;
    public static final ForgeConfigSpec.BooleanValue gorgonCanUseMeleeAttack;

    static {
        BUILDER.push("Gorgon Config (蛇发女妖配置)");
        
        gorgonCanUsePetrify = BUILDER
                .comment("蛇发女妖是否能使用石化 (Whether the gorgon can use petrify ability)")
                .define("gorgonCanUsePetrify", true);
                
        gorgonCanUsePoisonFang = BUILDER
                .comment("蛇发女妖是否能使用毒牙 (Whether the gorgon can use poison fang attack)")
                .define("gorgonCanUsePoisonFang", true);
                
        gorgonCanUseMeleeAttack = BUILDER
                .comment("蛇发女妖是否能近战攻击 (Whether the gorgon can use melee attack)")
                .define("gorgonCanUseMeleeAttack", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}