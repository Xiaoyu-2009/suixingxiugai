package com.xiaoyu.suixingxiugai.config.twilightforest.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class LichConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue lichMaxHealth;
    public static final ForgeConfigSpec.DoubleValue lichMovementSpeed;
    public static final ForgeConfigSpec.DoubleValue lichAttackDamage;
    public static final ForgeConfigSpec.IntValue lichXpReward;
    public static final ForgeConfigSpec.BooleanValue enableLichShieldModification;
    public static final ForgeConfigSpec.IntValue lichShieldStrength;
    public static final ForgeConfigSpec.IntValue lichPhantomSummonMaxCount;
    public static final ForgeConfigSpec.BooleanValue lichPhantomCanBeAttacked;
    public static final ForgeConfigSpec.BooleanValue lichPhantomDropChest;
    public static final ForgeConfigSpec.BooleanValue lichCanSpawnPhantom;
    public static final ForgeConfigSpec.BooleanValue lichCanAbsorbMinions;
    public static final ForgeConfigSpec.BooleanValue lichCanAbsorbMobsInPhase2;
    public static final ForgeConfigSpec.BooleanValue lichCanAbsorbMinionsInPhase2;
    public static final ForgeConfigSpec.BooleanValue lichCanAbsorbMobsInPhase3;
    public static final ForgeConfigSpec.DoubleValue lichPhase2MobAbsorbHealAmount;
    public static final ForgeConfigSpec.DoubleValue lichPhase2MobAbsorbHealthThreshold;
    public static final ForgeConfigSpec.DoubleValue lichPhase2MinionAbsorbHealAmount;
    public static final ForgeConfigSpec.DoubleValue lichPhase2MinionAbsorbHealthThreshold;
    public static final ForgeConfigSpec.DoubleValue lichPhase3MobAbsorbHealAmount;
    public static final ForgeConfigSpec.DoubleValue lichPhase3MobAbsorbHealthThreshold;
    public static final ForgeConfigSpec.BooleanValue lichPhase1CanTeleport;
    public static final ForgeConfigSpec.BooleanValue lichPhase2CanTeleport;
    public static final ForgeConfigSpec.BooleanValue lichPhase3CanTeleport;

    static {
        BUILDER.push("Lich Config (巫妖配置)");

        lichMaxHealth = BUILDER
                .comment("巫妖最大血量 (Lich max health)")
                .defineInRange("lichMaxHealth", 100.0D, 1.0D, Double.MAX_VALUE);
                
        lichMovementSpeed = BUILDER
                .comment("巫妖移动速度 (Lich movement speed)")
                .defineInRange("lichMovementSpeed", 0.45D, 0.0D, Double.MAX_VALUE);
                
        lichAttackDamage = BUILDER
                .comment("巫妖攻击伤害 (Lich attack damage)")
                .defineInRange("lichAttackDamage", 3.0D, 0.0D, Double.MAX_VALUE);
                
        lichXpReward = BUILDER
                .comment("巫妖经验值奖励 (Lich XP reward)")
                .defineInRange("lichXpReward", 217, 0, Integer.MAX_VALUE);

        enableLichShieldModification = BUILDER
                .comment("巫妖的护盾可被更多伤害破坏 (Whether Lich's shield can be broken by more types of damage)")
                .define("enableLichShieldModification", false);

        lichShieldStrength = BUILDER
                .comment("巫妖的护盾数量 (Number of Lich's shields)")
                .defineInRange("lichShieldStrength", 6, 1, Integer.MAX_VALUE);

        lichPhantomSummonMaxCount = BUILDER
                .comment("巫妖第一形态的幻影召唤最大数量 (Maximum number of phantoms summoned in Lich's first phase)")
                .defineInRange("lichPhantomSummonMaxCount", 4, 0, Integer.MAX_VALUE);
                
        lichPhantomCanBeAttacked = BUILDER
                .comment("巫妖第一形态的幻影是否能被攻击 (Whether phantoms in Lich's first phase can be attacked)")
                .define("lichPhantomCanBeAttacked", true);
                
        lichPhantomDropChest = BUILDER
                .comment("巫妖第一形态的幻影死亡时是否生成箱子 (Whether phantoms in Lich's first phase drop a chest when killed)")
                .define("lichPhantomDropChest", false);

        lichCanSpawnPhantom = BUILDER
                .comment("巫妖是否能产生幻影 - 最高优先级 (Whether the Lich can spawn phantoms - Highest priority)")
                .define("lichCanSpawnPhantom", true);
                
        lichCanAbsorbMinions = BUILDER
                .comment("巫妖是否能生命吸取 - 最高优先级 (Whether the Lich can absorb minions to heal - Highest priority)")
                .define("lichCanAbsorbMinions", true);
                
        lichCanAbsorbMobsInPhase2 = BUILDER
                .comment("巫妖第二形态时是否会吸收怪物 (Whether the Lich can absorb mobs in phase 2)")
                .define("lichCanAbsorbMobsInPhase2", true);
                
        lichCanAbsorbMinionsInPhase2 = BUILDER
                .comment("巫妖第二形态时是否会吸收仆从 (Whether the Lich can absorb minions in phase 2)")
                .define("lichCanAbsorbMinionsInPhase2", true);
                
        lichCanAbsorbMobsInPhase3 = BUILDER
                .comment("巫妖第三形态时是否会吸收怪物 (Whether the Lich can absorb mobs in phase 3)")
                .define("lichCanAbsorbMobsInPhase3", true);

        lichPhase2MobAbsorbHealAmount = BUILDER
                .comment("巫妖第二形态时吸收怪物回复的生命值 (Health amount restored when Lich absorbs mobs in phase 2)")
                .defineInRange("lichPhase2MobAbsorbHealAmount", 2.0D, 0.0D, Double.MAX_VALUE);
                
        lichPhase2MobAbsorbHealthThreshold = BUILDER
                .comment("巫妖第二形态时吸收怪物回复的血量阈值 (Health threshold for Lich to absorb mobs in phase 2)")
                .defineInRange("lichPhase2MobAbsorbHealthThreshold", 50.0D, 0.0D, Double.MAX_VALUE);
                
        lichPhase2MinionAbsorbHealAmount = BUILDER
                .comment("巫妖第二形态时吸收仆从回复的生命值 (Health amount restored when Lich absorbs minions in phase 2)")
                .defineInRange("lichPhase2MinionAbsorbHealAmount", 10.0D, 0.0D, Double.MAX_VALUE);
                
        lichPhase2MinionAbsorbHealthThreshold = BUILDER
                .comment("巫妖第二形态时吸收仆从回复的血量阈值 (Health threshold for Lich to absorb minions in phase 2)")
                .defineInRange("lichPhase2MinionAbsorbHealthThreshold", 50.0D, 0.0D, Double.MAX_VALUE);

        lichPhase3MobAbsorbHealAmount = BUILDER
                .comment("巫妖第三形态时吸收怪物回复的生命值 (Health amount restored when Lich absorbs mobs in phase 3)")
                .defineInRange("lichPhase3MobAbsorbHealAmount", 20.0D, 0.0D, Double.MAX_VALUE);
                
        lichPhase3MobAbsorbHealthThreshold = BUILDER
                .comment("巫妖第三形态时吸收怪物回复的血量阈值 (Health threshold for Lich to absorb mobs in phase 3)")
                .defineInRange("lichPhase3MobAbsorbHealthThreshold", 80.0D, 0.0D, Double.MAX_VALUE);

        lichPhase1CanTeleport = BUILDER
                .comment("巫妖第一形态是否会瞬移 (Whether the Lich can teleport in phase 1)")
                .define("lichPhase1CanTeleport", true);

        lichPhase2CanTeleport = BUILDER
                .comment("巫妖第二形态是否会瞬移 (Whether the Lich can teleport in phase 2)")
                .define("lichPhase2CanTeleport", true);

        lichPhase3CanTeleport = BUILDER
                .comment("巫妖第三形态是否会瞬移 (Whether the Lich can teleport in phase 3)")
                .define("lichPhase3CanTeleport", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}