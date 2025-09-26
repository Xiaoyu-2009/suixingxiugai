package com.xiaoyu.suixingxiugai.config.twilightforest.entity.lich;

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

    static {
        BUILDER.push("TwilightForest (暮色森林)");
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
                
        BUILDER.pop();
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}