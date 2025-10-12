package com.xiaoyu.suixingxiugai.config.alex.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class PocketSandConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue pocketSandEffectLevel;
    public static final ForgeConfigSpec.ConfigValue<String> pocketSandEffectId;
    public static final ForgeConfigSpec.IntValue pocketSandEffectDuration;
    public static final ForgeConfigSpec.DoubleValue pocketSandDamage;

    static {
        BUILDER.push("Pocket Sand Config (含沙射之袋配置)");
        
        pocketSandDamage = BUILDER
                .comment("含沙射之袋造成的伤害 (Pocket Sand damage)")
                .defineInRange("pocketSandDamage", 2.5, 0.0, Double.MAX_VALUE);

        pocketSandEffectLevel = BUILDER
                .comment("含沙射之袋施加药水效果的等级 (Pocket Sand effect level)")
                .defineInRange("pocketSandEffectLevel", 0, 0, Integer.MAX_VALUE);
                
        pocketSandEffectId = BUILDER
                .comment("含沙射之袋施加药水效果的ID (Pocket Sand effect ID)")
                .define("pocketSandEffectId", "minecraft:blindness");
                
        pocketSandEffectDuration = BUILDER
                .comment("含沙射之袋施加药水效果的持续时间 (tick) (Pocket Sand effect duration in ticks)")
                .defineInRange("pocketSandEffectDuration", 100, 0, Integer.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}