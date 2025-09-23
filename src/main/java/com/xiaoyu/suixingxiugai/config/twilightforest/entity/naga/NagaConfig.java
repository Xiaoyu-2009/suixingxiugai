package com.xiaoyu.suixingxiugai.config.twilightforest.entity.naga;

import net.minecraftforge.common.ForgeConfigSpec;

public class NagaConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue nagaStunDuration;
    public static final ForgeConfigSpec.IntValue nagaStunDamageThreshold;
    public static final ForgeConfigSpec.DoubleValue nagaMaxHealth;
    public static final ForgeConfigSpec.DoubleValue nagaMovementSpeed;
    public static final ForgeConfigSpec.DoubleValue nagaAttackDamage;
    public static final ForgeConfigSpec.DoubleValue nagaSegmentAttackDamage;
    public static final ForgeConfigSpec.DoubleValue nagaFollowRange;
    public static final ForgeConfigSpec.DoubleValue nagaKnockbackResistance;
    public static final ForgeConfigSpec.IntValue nagaXpReward;
    public static final ForgeConfigSpec.IntValue nagaDifficultyHealthBoostNormal;
    public static final ForgeConfigSpec.IntValue nagaDifficultyHealthBoostHard;
    public static final ForgeConfigSpec.DoubleValue nagaSegmentAttackDamageMultiplierAgainstAnimals;
    public static final ForgeConfigSpec.IntValue nagaHealingDelay;
    public static final ForgeConfigSpec.IntValue nagaCourtyardBoundX;
    public static final ForgeConfigSpec.IntValue nagaCourtyardBoundZ;
    public static final ForgeConfigSpec.IntValue nagaCourtyardBoundY;
    public static final ForgeConfigSpec.IntValue nagaShieldDamageOnCharge;
    public static final ForgeConfigSpec.IntValue nagaShieldCooldown;
    public static final ForgeConfigSpec.DoubleValue nagaHeadChargePushForce;
    public static final ForgeConfigSpec.DoubleValue nagaHeadChargeRecoilForce;
    public static final ForgeConfigSpec.DoubleValue nagaSegmentChargePushForce;
    public static final ForgeConfigSpec.DoubleValue nagaSegmentChargeRecoilForce;
    public static final ForgeConfigSpec.DoubleValue nagaAttackKnockbackForce;
    public static final ForgeConfigSpec.DoubleValue nagaSegmentDamageTransferRatio;
    public static final ForgeConfigSpec.BooleanValue nagaCanDestroyBlocks;
    public static final ForgeConfigSpec.IntValue nagaDeathAnimationStartDelay;
    public static final ForgeConfigSpec.IntValue nagaDeathAnimationDuration;

    static {
        BUILDER.push("TwilightForest (暮色森林)");
        BUILDER.push("Naga Config (娜迦配置)");
        
        nagaMaxHealth = BUILDER
                .comment("娜迦最大血量 (Naga max health)")
                .defineInRange("nagaMaxHealth", 120.0D, 1.0D, Double.MAX_VALUE);
                
        nagaMovementSpeed = BUILDER
                .comment("娜迦移动速度 (Naga movement speed)")
                .defineInRange("nagaMovementSpeed", 0.5D, 0.0D, Double.MAX_VALUE);
                
        nagaAttackDamage = BUILDER
                .comment("娜迦头部攻击伤害 (Naga head attack damage)")
                .defineInRange("nagaAttackDamage", 5.0D, 0.0D, Double.MAX_VALUE);
                
        nagaSegmentAttackDamage = BUILDER
                .comment("娜迦身体段攻击伤害 (Naga segment attack damage)")
                .defineInRange("nagaSegmentAttackDamage", 2.0D, 0.0D, Double.MAX_VALUE);

        nagaSegmentAttackDamageMultiplierAgainstAnimals = BUILDER
                .comment("娜迦身体段对动物攻击伤害倍数 (Naga segment attack damage multiplier against animals)")
                .defineInRange("nagaSegmentAttackDamageMultiplierAgainstAnimals", 3.0D, 1.0D, Double.MAX_VALUE);
                
        nagaFollowRange = BUILDER
                .comment("娜迦跟随距离 (Naga follow range)")
                .defineInRange("nagaFollowRange", 80.0D, 0.0D, Double.MAX_VALUE);
                
        nagaKnockbackResistance = BUILDER
                .comment("娜迦击退抗性 (Naga knockback resistance)")
                .defineInRange("nagaKnockbackResistance", 0.25D, 0.0D, 1.0D);
                
        nagaXpReward = BUILDER
                .comment("娜迦经验值奖励 (Naga XP reward)")
                .defineInRange("nagaXpReward", 217, 0, Integer.MAX_VALUE);
                
        nagaDifficultyHealthBoostNormal = BUILDER
                .comment("娜迦在普通难度下的血量加成 (Naga health boost on normal difficulty)")
                .defineInRange("nagaDifficultyHealthBoostNormal", 80, 0, Integer.MAX_VALUE);
                
        nagaDifficultyHealthBoostHard = BUILDER
                .comment("娜迦在困难难度下的血量加成 (Naga health boost on hard difficulty)")
                .defineInRange("nagaDifficultyHealthBoostHard", 130, 0, Integer.MAX_VALUE);
        
        nagaStunDuration = BUILDER
                .comment("娜迦眩晕持续时间 (Naga stun duration)")
                .defineInRange("nagaStunDuration", 60, 1, Integer.MAX_VALUE);
                
        nagaStunDamageThreshold = BUILDER
                .comment("娜迦眩晕伤害阈值 (Naga stun damage threshold)")
                .defineInRange("nagaStunDamageThreshold", 15, 1, Integer.MAX_VALUE);

        nagaHealingDelay = BUILDER
                .comment("娜迦生命值恢复延迟时间 (tick) (Naga healing delay time (tick))")
                .defineInRange("nagaHealingDelay", 600, 0, Integer.MAX_VALUE);
                
        nagaCourtyardBoundX = BUILDER
                .comment("当实体或娜迦走出庭院边界时，娜迦会尝试回到中心点 (When entities or Naga go beyond the courtyard boundary, Naga will try to return to the center point)",
                "娜迦庭院边界范围 X方向 (Naga courtyard boundary range X direction)")
                .defineInRange("nagaCourtyardBoundX", 46, 1, Integer.MAX_VALUE);
                
        nagaCourtyardBoundZ = BUILDER
                .comment("娜迦庭院边界范围 Z方向 (Naga courtyard boundary range Z direction)")
                .defineInRange("nagaCourtyardBoundZ", 46, 1, Integer.MAX_VALUE);
                
        nagaCourtyardBoundY = BUILDER
                .comment("娜迦庭院边界范围 Y方向 (Naga courtyard boundary range Y direction)")
                .defineInRange("nagaCourtyardBoundY", 7, 1, Integer.MAX_VALUE);
                
        nagaShieldDamageOnCharge = BUILDER
                .comment("娜迦冲撞盾牌后盾牌的耐久消耗 (Naga shield durability consumption after charging shield)")
                .defineInRange("nagaShieldDamageOnCharge", 5, 0, Integer.MAX_VALUE);
                
        nagaShieldCooldown = BUILDER
                .comment("娜迦冲撞盾牌后盾牌的冷却时间 (tick) (Naga shield cooldown after charge (tick))")
                .defineInRange("nagaShieldCooldown", 200, 0, Integer.MAX_VALUE);
                
        nagaHeadChargePushForce = BUILDER
                .comment("娜迦头部推力系数 (Naga head push force coefficient)")
                .defineInRange("nagaHeadChargePushForce", 2.0D, 0.0D, Double.MAX_VALUE);
                
        nagaHeadChargeRecoilForce = BUILDER
                .comment("娜迦头部反作用力系数 (Naga head recoil force coefficient)")
                .defineInRange("nagaHeadChargeRecoilForce", 0.0D, 0.0D, Double.MAX_VALUE);
                
        nagaSegmentChargePushForce = BUILDER
                .comment("娜迦身体段推力系数 (Naga segment push force coefficient)")
                .defineInRange("nagaSegmentChargePushForce", 2.0D, 0.0D, Double.MAX_VALUE);
                
        nagaSegmentChargeRecoilForce = BUILDER
                .comment("娜迦身体段反作用力系数 (Naga segment recoil force coefficient)")
                .defineInRange("nagaSegmentChargeRecoilForce", 0.0D, 0.0D, Double.MAX_VALUE);
                
        nagaAttackKnockbackForce = BUILDER
                .comment("娜迦冲撞盾牌自身受到的伤害 (Naga self damage when charging shield)")
                .defineInRange("nagaAttackKnockbackForce", 2.0D, 0.0D, Double.MAX_VALUE);
                
        nagaSegmentDamageTransferRatio = BUILDER
                .comment("娜迦每段身体受到伤害时传递给主体的伤害比例 (Naga segment damage transfer ratio to main body)")
                .defineInRange("nagaSegmentDamageTransferRatio", 2.0D/3.0D, 0.0D, 1.0D);
        nagaCanDestroyBlocks = BUILDER
                .comment("娜迦是否可以破坏方块 (Whether Naga can destroy blocks)")
                .define("nagaCanDestroyBlocks", true);

        nagaDeathAnimationStartDelay = BUILDER
                .comment("娜迦死亡动画开始延迟 (tick) (Naga death animation start delay (tick))")
                .defineInRange("nagaDeathAnimationStartDelay", 24, 0, Integer.MAX_VALUE);
                
        nagaDeathAnimationDuration = BUILDER
                .comment("娜迦死亡动画持续时间 (tick) (Naga death animation duration (tick))")
                .defineInRange("nagaDeathAnimationDuration", 120, 1, Integer.MAX_VALUE);

        BUILDER.pop();
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}