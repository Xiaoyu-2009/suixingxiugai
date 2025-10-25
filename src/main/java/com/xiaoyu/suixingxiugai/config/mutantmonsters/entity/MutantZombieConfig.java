package com.xiaoyu.suixingxiugai.config.mutantmonsters.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class MutantZombieConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue MUTANT_ZOMBIE_RESURRECTION_COUNT;
    public static final ForgeConfigSpec.BooleanValue MUTANT_ZOMBIE_ENABLE_SLAM_GROUND_ATTACK;
    public static final ForgeConfigSpec.BooleanValue MUTANT_ZOMBIE_ENABLE_THROW_ATTACK;
    public static final ForgeConfigSpec.BooleanValue MUTANT_ZOMBIE_ENABLE_ROAR_ATTACK;

    static {
        BUILDER.push("Mutant Zombie Config (突变僵尸配置)");

        MUTANT_ZOMBIE_RESURRECTION_COUNT = BUILDER
                .comment("突变僵尸倒下苏醒次数 (Mutant zombie resurrection count)")
                .defineInRange("mutantZombieResurrectionCount", 4, 1, Integer.MAX_VALUE);

        MUTANT_ZOMBIE_ENABLE_SLAM_GROUND_ATTACK = BUILDER
                .comment("突变僵尸是否能使用冲击波 (Whether mutant zombie can use slam ground attack)")
                .define("mutantZombieEnableSlamGroundAttack", true);
                
        MUTANT_ZOMBIE_ENABLE_THROW_ATTACK = BUILDER
                .comment("突变僵尸是否能使用投掷攻击 (Whether mutant zombie can use throw attack)")
                .define("mutantZombieEnableThrowAttack", true);

        MUTANT_ZOMBIE_ENABLE_ROAR_ATTACK = BUILDER
                .comment("突变僵尸是否能使用怒吼 (Whether mutant zombie can use roar attack)")
                .define("mutantZombieEnableRoarAttack", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}