package com.xiaoyu.suixingxiugai.config.twilightforest.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class KnightPhantomConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue knightPhantomMaxHealth;
    public static final ForgeConfigSpec.DoubleValue knightPhantomFlightSpeed;
    public static final ForgeConfigSpec.DoubleValue knightPhantomAttackDamage;
    public static final ForgeConfigSpec.IntValue knightPhantomXpReward;
    public static final ForgeConfigSpec.DoubleValue knightPhantomArmor;
    public static final ForgeConfigSpec.BooleanValue knightPhantomCanNoClip;
    public static final ForgeConfigSpec.ConfigValue<String> knightPhantomHelmetItem;
    public static final ForgeConfigSpec.ConfigValue<String> knightPhantomChestplateItem;
    public static final ForgeConfigSpec.ConfigValue<String> knightPhantomLeggingsItem;
    public static final ForgeConfigSpec.ConfigValue<String> knightPhantomBootsItem;
    public static final ForgeConfigSpec.ConfigValue<String> knightPhantomMainHandNumber0Item;
    public static final ForgeConfigSpec.ConfigValue<String> knightPhantomMainHandNumber1Item;
    public static final ForgeConfigSpec.ConfigValue<String> knightPhantomMainHandNumber2Item;
    public static final ForgeConfigSpec.ConfigValue<String> knightPhantomOffHandItem;

    static {
        BUILDER.push("Knight Phantom Config (幻影骑士配置)");

        knightPhantomMaxHealth = BUILDER
                .comment("幻影骑士最大血量 (Knight Phantom max health)")
                .defineInRange("knightPhantomMaxHealth", 35.0D, 1.0D, Double.MAX_VALUE);
                
        knightPhantomFlightSpeed = BUILDER
                .comment("幻影骑士飞行速度 (Knight Phantom flight speed)")
                .defineInRange("knightPhantomFlightSpeed", 0.1D, 0.0D, Double.MAX_VALUE);
                
        knightPhantomAttackDamage = BUILDER
                .comment("幻影骑士攻击伤害 (Knight Phantom attack damage)")
                .defineInRange("knightPhantomAttackDamage", 1.0D, 0.0D, Double.MAX_VALUE);
                
        knightPhantomXpReward = BUILDER
                .comment("幻影骑士经验值奖励 (Knight Phantom XP reward)")
                .defineInRange("knightPhantomXpReward", 93, 0, Integer.MAX_VALUE);

        knightPhantomArmor = BUILDER
                .comment("幻影骑士护甲值 (Knight Phantom armor value)")
                .defineInRange("knightPhantomArmor", 4.0D, 0.0D, Double.MAX_VALUE);

        knightPhantomCanNoClip = BUILDER
                .comment("幻影骑士是否能穿过方块 (Whether Knight Phantom can pass through blocks)")
                .define("knightPhantomCanNoClip", true);

        knightPhantomHelmetItem = BUILDER
                .comment("幻影骑士佩戴的头盔 (Knight Phantom helmet item)")
                .define("knightPhantomHelmetItem", "twilightforest:phantom_helmet");
                
        knightPhantomChestplateItem = BUILDER
                .comment("幻影骑士佩戴的胸甲 (Knight Phantom chestplate item)")
                .define("knightPhantomChestplateItem", "twilightforest:phantom_chestplate");
                
        knightPhantomLeggingsItem = BUILDER
                .comment("幻影骑士佩戴的护腿 (Knight Phantom leggings item)")
                .define("knightPhantomLeggingsItem", "minecraft:air");
                
        knightPhantomBootsItem = BUILDER
                .comment("幻影骑士佩戴的靴子 (Knight Phantom boots item)")
                .define("knightPhantomBootsItem", "minecraft:air");

        knightPhantomMainHandNumber0Item = BUILDER
                .comment("幻影骑士主手序号0物品 (Knight Phantom main hand number 0 item)")
                .define("knightPhantomMainHandNumber0Item", "twilightforest:knightmetal_sword");
                
        knightPhantomMainHandNumber1Item = BUILDER
                .comment("幻影骑士主手序号1物品 (Knight Phantom main hand number 1 item)")
                .define("knightPhantomMainHandNumber1Item", "twilightforest:knightmetal_axe");
                
        knightPhantomMainHandNumber2Item = BUILDER
                .comment("幻影骑士主手序号2物品 (Knight Phantom main hand number 2 item)")
                .define("knightPhantomMainHandNumber2Item", "twilightforest:knightmetal_pickaxe");

        knightPhantomOffHandItem = BUILDER
                .comment("幻影骑士副手物品 (Knight Phantom off hand item)")
                .define("knightPhantomOffHandItem", "minecraft:air");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}