package com.xiaoyu.suixingxiugai.config.twilightforest.item.wand;

import net.minecraftforge.common.ForgeConfigSpec;

public class LifedrainScepterConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.IntValue lifedrainScepterUses;
    public static final ForgeConfigSpec.IntValue lifedrainScepterCooldown;

    static {
        BUILDER.push("Lifedrain Scepter Config (吸血权杖配置)");
        
        lifedrainScepterUses = BUILDER
                .comment("吸血权杖使用次数 (Number of uses for lifedrain scepter)")
                .defineInRange("lifedrainScepterUses", 99, 1, Integer.MAX_VALUE);

        lifedrainScepterCooldown = BUILDER
                .comment("吸血权杖冷却时间 (Cooldown time for lifedrain scepter in ticks)")
                .defineInRange("lifedrainScepterCooldown", 0, 0, Integer.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}