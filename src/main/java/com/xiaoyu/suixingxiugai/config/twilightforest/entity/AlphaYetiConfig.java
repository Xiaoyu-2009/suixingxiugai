package com.xiaoyu.suixingxiugai.config.twilightforest.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class AlphaYetiConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue alphaYetiMaxHealth;
    public static final ForgeConfigSpec.DoubleValue alphaYetiMovementSpeed;
    public static final ForgeConfigSpec.DoubleValue alphaYetiAttackDamage;
    public static final ForgeConfigSpec.DoubleValue alphaYetiFollowRange;
    public static final ForgeConfigSpec.DoubleValue alphaYetiKnockbackResistance;
    public static final ForgeConfigSpec.IntValue alphaYetiXpReward;
    public static final ForgeConfigSpec.BooleanValue alphaYetiCanThrowEntity;
    public static final ForgeConfigSpec.BooleanValue alphaYetiCanUseIceBomb;
    public static final ForgeConfigSpec.BooleanValue alphaYetiCanUseIceRampage;
    public static final ForgeConfigSpec.IntValue alphaYetiIceRampageTimeout;
    public static final ForgeConfigSpec.IntValue alphaYetiIceRampageDuration;
    public static final ForgeConfigSpec.IntValue alphaYetiIceRampageTiredDuration;
    public static final ForgeConfigSpec.BooleanValue alphaYetiCanUseFallingIce;

    static {
        BUILDER.push("Alpha Yeti Config (雪怪首领配置)");

        alphaYetiMaxHealth = BUILDER
                .comment("雪怪首领最大血量 (Alpha Yeti max health)")
                .defineInRange("alphaYetiMaxHealth", 200.0D, 1.0D, Double.MAX_VALUE);
                
        alphaYetiMovementSpeed = BUILDER
                .comment("雪怪首领移动速度 (Alpha Yeti movement speed)")
                .defineInRange("alphaYetiMovementSpeed", 0.38D, 0.0D, Double.MAX_VALUE);
                
        alphaYetiAttackDamage = BUILDER
                .comment("雪怪首领攻击伤害 (Alpha Yeti attack damage)")
                .defineInRange("alphaYetiAttackDamage", 1.0D, 0.0D, Double.MAX_VALUE);
                
        alphaYetiFollowRange = BUILDER
                .comment("雪怪首领跟随范围 (Alpha Yeti follow range)")
                .defineInRange("alphaYetiFollowRange", 40.0D, 0.0D, Double.MAX_VALUE);
                
        alphaYetiKnockbackResistance = BUILDER
                .comment("雪怪首领击退抗性 (Alpha Yeti knockback resistance)")
                .defineInRange("alphaYetiKnockbackResistance", 0.5D, 0.0D, 1.0D);
                
        alphaYetiXpReward = BUILDER
                .comment("雪怪首领经验值奖励 (Alpha Yeti XP reward)")
                .defineInRange("alphaYetiXpReward", 317, 0, Integer.MAX_VALUE);
                
        alphaYetiCanThrowEntity = BUILDER
                .comment("雪怪首领是否会抛掷实体 (Whether Alpha Yeti can throw entities)")
                .define("alphaYetiCanThrowEntity", true);

        alphaYetiCanUseIceBomb = BUILDER
                .comment("雪怪首领是否会使用寒冰炸弹 (Whether Alpha Yeti can use ice bombs)")
                .define("alphaYetiCanUseIceBomb", true);

        alphaYetiCanUseIceRampage = BUILDER
                .comment("雪怪首领是否会使用寒冰狂暴 (Whether Alpha Yeti can use ice rampage)")
                .define("alphaYetiCanUseIceRampage", true);

        alphaYetiIceRampageTimeout = BUILDER
                .comment("雪怪首领寒冰狂暴技能冷却时间，单位：tick (Alpha Yeti ice rampage cooldown time, unit: tick)")
                .defineInRange("alphaYetiIceRampageTimeout", 10, 0, Integer.MAX_VALUE);

        alphaYetiIceRampageDuration = BUILDER
                .comment("雪怪首领寒冰狂暴技能持续时间，单位：tick (Alpha Yeti ice rampage duration, unit: tick)")
                .defineInRange("alphaYetiIceRampageDuration", 180, 0, Integer.MAX_VALUE);

        alphaYetiIceRampageTiredDuration = BUILDER
                .comment("雪怪首领寒冰狂暴技能结束后疲劳时间，单位：tick (Alpha Yeti ice rampage tired duration, unit: tick)")
                .defineInRange("alphaYetiIceRampageTiredDuration", 100, 0, Integer.MAX_VALUE);

        alphaYetiCanUseFallingIce = BUILDER
                .comment("雪怪首领在寒冰狂暴期间是否会使用落冰 (Whether Alpha Yeti can use falling ice during ice rampage)")
                .define("alphaYetiCanUseFallingIce", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}