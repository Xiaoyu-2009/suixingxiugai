package com.xiaoyu.suixingxiugai.config.alex.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class BloodSprayerConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue bloodSprayerDamage;

    static {
        BUILDER.push("Blood Sprayer Config (血液喷射器配置)");

        bloodSprayerDamage = BUILDER
                .comment("血液喷射器造成的伤害 (Blood Sprayer damage)")
                .defineInRange("bloodSprayerDamage", 4.0, 0.0, Double.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}