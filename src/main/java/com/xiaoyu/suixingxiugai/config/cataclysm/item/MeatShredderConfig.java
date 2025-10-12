package com.xiaoyu.suixingxiugai.config.cataclysm.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class MeatShredderConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.DoubleValue meatShredderRightClickDamage;

    static {
        BUILDER.push("Meat Shredder Config (绞肉锯配置)");

        meatShredderRightClickDamage = BUILDER
                .comment("绞肉锯右键造成的伤害 (Meat Shredder right-click damage)")
                .defineInRange("meatShredderRightClickDamage", 1.0, 0.0, Double.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}