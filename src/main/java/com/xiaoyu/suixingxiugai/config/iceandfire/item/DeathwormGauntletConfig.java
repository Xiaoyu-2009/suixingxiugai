package com.xiaoyu.suixingxiugai.config.iceandfire.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class DeathwormGauntletConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue deathwormGauntletDamage;
    public static final ForgeConfigSpec.DoubleValue deathwormGauntletPullForce;
    public static final ForgeConfigSpec.IntValue deathwormGauntletCooldown;
    public static final ForgeConfigSpec.DoubleValue deathwormGauntletRange;
    public static final ForgeConfigSpec.BooleanValue deathwormGauntletKnockbackResistanceReduction;

    static {
        BUILDER.push("IceAndFire (冰火传说)");
        BUILDER.push("Death Worm Gauntlet Config (死亡蠕虫手甲配置)");

        deathwormGauntletDamage = BUILDER
                .comment("死亡蠕虫手甲造成的伤害 (Damage dealt by the Death Worm Gauntlet)")
                .defineInRange("deathWormGauntletDamage", 3.0, 0.0, Double.MAX_VALUE);

        deathwormGauntletRange = BUILDER
                .comment("死亡蠕虫手甲的作用范围（方块） (Range of the Death Worm Gauntlet (blocks))")
                .defineInRange("deathWormGauntletRange", 5.0, 0.0, Double.MAX_VALUE);

        deathwormGauntletCooldown = BUILDER
                .comment("死亡蠕虫手甲使用间隔（tick） (Cooldown between uses of the Death Worm Gauntlet (tick))")
                .defineInRange("deathWormGauntletCooldown", 12, 0, Integer.MAX_VALUE);

        deathwormGauntletPullForce = BUILDER
                .comment("拉拽目标到玩家位置的力度 (Force with which targets are pulled towards the player)")
                .defineInRange("deathWormGauntletPullForce", 0.5, 0.0, Double.MAX_VALUE);

        deathwormGauntletKnockbackResistanceReduction = BUILDER
                .comment("击退抗性是否降低拉拽力度 (Whether knockback resistance reduces pull force)")
                .define("deathWormGauntletKnockbackResistanceReduction", true);

        BUILDER.pop();
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}