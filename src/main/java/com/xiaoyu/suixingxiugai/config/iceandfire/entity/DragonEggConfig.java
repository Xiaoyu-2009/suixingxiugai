package com.xiaoyu.suixingxiugai.config.iceandfire.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class DragonEggConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue fireDragonEggHatchTime;
    public static final ForgeConfigSpec.IntValue iceDragonEggHatchTime;
    public static final ForgeConfigSpec.IntValue lightningDragonEggHatchTime;
    public static final ForgeConfigSpec.IntValue iceDragonEggFreezeProbability;
    public static final ForgeConfigSpec.BooleanValue lightningDragonEggSpawnLightning;

    static {
        BUILDER.push("Dragon Egg Config (龙蛋配置)");

        fireDragonEggHatchTime = BUILDER
                .comment("火龙蛋孵化时间 (Fire dragon egg hatch time in ticks)")
                .defineInRange("fireDragonEggHatchTime", 7200, 1, Integer.MAX_VALUE);

        iceDragonEggHatchTime = BUILDER
                .comment("冰龙蛋孵化时间 (Ice dragon egg hatch time in ticks)")
                .defineInRange("iceDragonEggHatchTime", 7200, 1, Integer.MAX_VALUE);

        lightningDragonEggHatchTime = BUILDER
                .comment("电龙蛋孵化时间 (Lightning dragon egg hatch time in ticks)")
                .defineInRange("lightningDragonEggHatchTime", 7200, 1, Integer.MAX_VALUE);
                
        iceDragonEggFreezeProbability = BUILDER
                .comment("冰龙蛋在水中结冰概率分母 (Ice dragon egg freeze probability denominator, 1 in X chance)")
                .defineInRange("iceDragonEggFreezeProbability", 500, 1, Integer.MAX_VALUE);
                
        lightningDragonEggSpawnLightning = BUILDER
                .comment("电龙蛋孵化时是否降下雷电 (Whether lightning dragon egg spawns lightning when hatching)")
                .define("lightningDragonEggSpawnLightning", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}