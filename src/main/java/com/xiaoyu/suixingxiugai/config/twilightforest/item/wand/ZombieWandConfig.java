package com.xiaoyu.suixingxiugai.config.twilightforest.item.wand;

import net.minecraftforge.common.ForgeConfigSpec;

public class ZombieWandConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.IntValue zombieWandUses;
    public static final ForgeConfigSpec.IntValue zombieWandCooldown;

    static {
        BUILDER.push("Zombie Wand Config (僵尸权杖配置)");
        
        zombieWandUses = BUILDER
                .comment("僵尸权杖使用次数 (Number of uses for zombie wand)")
                .defineInRange("zombieWandUses", 9, 1, Integer.MAX_VALUE);
                
        zombieWandCooldown = BUILDER
                .comment("僵尸权杖冷却时间 (Cooldown time for zombie wand in ticks)")
                .defineInRange("zombieWandCooldown", 0, 0, Integer.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}