package com.xiaoyu.suixingxiugai.config.iceandfire.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class CyclopsEyeConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue cyclopsEyePotionEffectRadius;
    public static final ForgeConfigSpec.IntValue cyclopsEyePotionEffectLevel;
    public static final ForgeConfigSpec.ConfigValue<String> cyclopsEyePotionEffectId;
    public static final ForgeConfigSpec.IntValue cyclopsEyePotionEffectDuration;
    public static final ForgeConfigSpec.IntValue cyclopsEyeMinAffectedMobs;
    public static final ForgeConfigSpec.IntValue cyclopsEyeDurabilityReduction;
    public static final ForgeConfigSpec.IntValue cyclopsEyeDurabilityReductionSpeed;
    public static final ForgeConfigSpec.BooleanValue cyclopsEyePlayBreakSound;
    public static final ForgeConfigSpec.ConfigValue<String> cyclopsEyeTargetEntityType;

    static {
        BUILDER.push("Cyclops Eye Configuration (巨人独眼配置)");
        
        cyclopsEyePotionEffectRadius = BUILDER
                .comment("巨人独眼施加药水效果的半径范围 (Cyclops eye potion effect radius)")
                .defineInRange("cyclopsEyePotionEffectRadius", 10.0, 1.0, Double.MAX_VALUE);

        cyclopsEyePotionEffectLevel = BUILDER
                .comment("巨人独眼施加药水效果的等级 (Cyclops eye potion effect level)")
                .defineInRange("cyclopsEyePotionEffectLevel", 1, 0, Integer.MAX_VALUE);

        cyclopsEyePotionEffectId = BUILDER
                .comment("巨人独眼施加药水效果的ID (Cyclops eye potion effect ID)")
                .define("cyclopsEyePotionEffectId", "minecraft:weakness");
                
        cyclopsEyePotionEffectDuration = BUILDER
                .comment("巨人独眼施加药水效果的持续时间 (tick) (Cyclops eye potion effect duration in (tick))")
                .defineInRange("cyclopsEyePotionEffectDuration", 20, 1, Integer.MAX_VALUE);
                
        cyclopsEyeMinAffectedMobs = BUILDER
                .comment("作用范围内影响巨人独眼耐久度的最少生物数量 (Minimum number of mobs in range to affect cyclops eye durability)")
                .defineInRange("cyclopsEyeMinAffectedMobs", 1, 1, Integer.MAX_VALUE);
                
        cyclopsEyeDurabilityReduction = BUILDER
                .comment("作用范围内巨人独眼耐久度的减少量 (Cyclops eye durability reduction amount)")
                .defineInRange("cyclopsEyeDurabilityReduction", 1, 1, Integer.MAX_VALUE);
                
        cyclopsEyeDurabilityReductionSpeed = BUILDER
                .comment("作用范围内巨人独眼耐久度减少的速度 (tick) (Cyclops eye durability reduction speed (tick))")
                .defineInRange("cyclopsEyeDurabilityReductionSpeed", 120, 1, Integer.MAX_VALUE);
                
        cyclopsEyePlayBreakSound = BUILDER
                .comment("是否播放巨人独眼碎裂音效 (Whether to play cyclops eye break sound)")
                .define("cyclopsEyePlayBreakSound", true);
                
        cyclopsEyeTargetEntityType = BUILDER
                .comment("巨人独眼作用实体类型 (可选: mob, living, player, all, animal, monster, ambient, water_animal, flying)",
                "(Cyclops eye target entity type (options: mob, living, player, all, animal, monster, ambient, water_animal, flying))")
                .define("cyclopsEyeTargetEntityType", "living");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}