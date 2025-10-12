package com.xiaoyu.suixingxiugai.config.alex.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class HemolymphBlasterConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue hemolymphBlasterDamage;

    static {
        BUILDER.push("Hemolymph Blaster Config (血淋巴喷射器配置)");

        hemolymphBlasterDamage = BUILDER
                .comment("血淋巴喷射器造成的伤害 (Hemolymph Blaster damage)")
                .defineInRange("hemolymphBlasterDamage", 7.0, 0.0, Double.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}