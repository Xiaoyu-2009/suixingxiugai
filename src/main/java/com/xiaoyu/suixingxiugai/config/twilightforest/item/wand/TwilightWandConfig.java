package com.xiaoyu.suixingxiugai.config.twilightforest.item.wand;

import net.minecraftforge.common.ForgeConfigSpec;

public class TwilightWandConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.IntValue twilightWandUses;
    public static final ForgeConfigSpec.IntValue twilightWandCooldown;

    static {
        BUILDER.push("Twilight Wand Config (黄昏权杖配置)");
        
        twilightWandUses = BUILDER
                .comment("黄昏权杖使用次数 (Number of uses for twilight wand)")
                .defineInRange("twilightWandUses", 99, 1, Integer.MAX_VALUE);
                
        twilightWandCooldown = BUILDER
                .comment("黄昏权杖冷却时间 (Cooldown time for twilight wand in ticks)")
                .defineInRange("twilightWandCooldown", 0, 0, Integer.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}