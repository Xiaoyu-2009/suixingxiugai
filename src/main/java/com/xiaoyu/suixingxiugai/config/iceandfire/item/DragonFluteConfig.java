package com.xiaoyu.suixingxiugai.config.iceandfire.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class DragonFluteConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue dragonFluteAffectsWildDragons;
    public static final ForgeConfigSpec.IntValue dragonFluteCooldown;
    public static final ForgeConfigSpec.IntValue dragonFluteDistance;

    static {
        BUILDER.push("Dragon Flute Config (龙歌长笛配置)");
        
        dragonFluteAffectsWildDragons = BUILDER
                .comment("龙歌长笛是否能影响驯服外的生物 (Dragon Bone Flute Affects Wild Creatures)")
                .define("dragonFluteAffectsWildDragons", false);
                
        dragonFluteCooldown = BUILDER
                .comment("龙歌长笛冷却时间 (tick) (Dragon Bone Flute Cooldown Time (tick))")
                .defineInRange("dragonFluteCooldown", 60, 0, Integer.MAX_VALUE);
                
        dragonFluteDistance = BUILDER
                .comment("龙歌长笛影响范围 (chunks) (Dragon Bone Flute Effect Range (chunks))")
                .defineInRange("dragonFluteDistance", 8, 1, Integer.MAX_VALUE);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}