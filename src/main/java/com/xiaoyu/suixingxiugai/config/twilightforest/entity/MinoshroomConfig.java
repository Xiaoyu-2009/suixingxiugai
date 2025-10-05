package com.xiaoyu.suixingxiugai.config.twilightforest.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class MinoshroomConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue minoshroomMaxHealth;
    public static final ForgeConfigSpec.DoubleValue minoshroomKnockbackResistance;
    public static final ForgeConfigSpec.DoubleValue minoshroomMovementSpeed;
    public static final ForgeConfigSpec.DoubleValue minoshroomAttackDamage;
    public static final ForgeConfigSpec.IntValue minoshroomXpReward;
    public static final ForgeConfigSpec.DoubleValue minoshroomDifficultyAttackDamageBoostNormal;
    public static final ForgeConfigSpec.DoubleValue minoshroomDifficultyAttackDamageBoostHard;
    public static final ForgeConfigSpec.IntValue minoshroomGroundAttackChargeTime;
    public static final ForgeConfigSpec.DoubleValue minoshroomGroundAttackDamageMultiplier;
    public static final ForgeConfigSpec.BooleanValue minoshroomCanCharge;
    public static final ForgeConfigSpec.IntValue minoshroomChargeTime;
    public static final ForgeConfigSpec.BooleanValue minoshroomChargeCanBreakBlocks;
    public static final ForgeConfigSpec.DoubleValue minoshroomChargeKnockbackHeight;
    public static final ForgeConfigSpec.BooleanValue minoshroomCanGroundAttack;

    static {
        BUILDER.push("Minoshroom Config (米诺菇配置)");

        minoshroomMaxHealth = BUILDER
                .comment("米诺菇最大血量 (Minoshroom max health)")
                .defineInRange("minoshroomMaxHealth", 120.0D, 1.0D, Double.MAX_VALUE);
                
        minoshroomKnockbackResistance = BUILDER
                .comment("米诺菇击退抗性 (Minoshroom knockback resistance)")
                .defineInRange("minoshroomKnockbackResistance", 0.5D, 0.0D, 1.0D);
                
        minoshroomMovementSpeed = BUILDER
                .comment("米诺菇移动速度 (Minoshroom movement speed)")
                .defineInRange("minoshroomMovementSpeed", 0.25D, 0.0D, Double.MAX_VALUE);
                
        minoshroomAttackDamage = BUILDER
                .comment("米诺菇攻击伤害 (Minoshroom attack damage)")
                .defineInRange("minoshroomAttackDamage", 4.0D, 0.0D, Double.MAX_VALUE);

        minoshroomXpReward = BUILDER
                .comment("米诺菇经验值奖励 (Minoshroom XP reward)")
                .defineInRange("minoshroomXpReward", 100, 0, Integer.MAX_VALUE);
                
        minoshroomDifficultyAttackDamageBoostNormal = BUILDER
                .comment("米诺菇在普通难度下的攻击伤害加成 (Minoshroom attack damage boost on normal difficulty)")
                .defineInRange("minoshroomDifficultyAttackDamageBoostNormal", 3.0D, 0.0D, Double.MAX_VALUE);
                
        minoshroomDifficultyAttackDamageBoostHard = BUILDER
                .comment("米诺菇在困难难度下的攻击伤害加成 (Minoshroom attack damage boost on hard difficulty)")
                .defineInRange("minoshroomDifficultyAttackDamageBoostHard", 6.0D, 0.0D, Double.MAX_VALUE);
                
        minoshroomGroundAttackChargeTime = BUILDER
                .comment("米诺菇手持斧子蓄力捶地的蓄力时间 (tick) (Minoshroom ground attack charge time (tick))")
                .defineInRange("minoshroomGroundAttackChargeTime", 30, 1, Integer.MAX_VALUE);
                
        minoshroomGroundAttackDamageMultiplier = BUILDER
                .comment("米诺菇手持斧子蓄力捶地的蓄力伤害倍数 (Minoshroom ground attack damage multiplier)")
                .defineInRange("minoshroomGroundAttackDamageMultiplier", 0.5D, 0.0D, Double.MAX_VALUE);

        minoshroomCanCharge = BUILDER
                .comment("米诺菇是否会冲撞 (Minoshroom can charge)")
                .define("minoshroomCanCharge", true);
                
        minoshroomChargeTime = BUILDER
                .comment("米诺菇冲撞蓄力时间 (tick) (Minoshroom charge time (tick))")
                .defineInRange("minoshroomChargeTime", 15, 1, Integer.MAX_VALUE);

        minoshroomChargeCanBreakBlocks = BUILDER
                .comment("米诺菇的冲撞是否能破坏经过的方块 (Minoshroom charge can break blocks)")
                .define("minoshroomChargeCanBreakBlocks", true);

        minoshroomChargeKnockbackHeight = BUILDER
                .comment("米诺菇的冲撞击飞高度 (Minoshroom charge knockback height)")
                .defineInRange("minoshroomChargeKnockbackHeight", 0.35D, 0.0D, Double.MAX_VALUE);

        minoshroomCanGroundAttack = BUILDER
                .comment("米诺菇是否会捶地 (Minoshroom can ground attack)")
                .define("minoshroomCanGroundAttack", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}