package com.xiaoyu.suixingxiugai.config.iceandfire.item;

import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class HydraHeartConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<? extends Double>> hydraHeartPotionThresholds;
    public static final ForgeConfigSpec.ConfigValue<List<? extends Integer>> hydraHeartPotionLevels;
    public static final ForgeConfigSpec.ConfigValue<String> hydraHeartPotionEffect;
    public static final ForgeConfigSpec.IntValue hydraHeartPotionDuration;
    public static final ForgeConfigSpec.BooleanValue hydraHeartApplyEffectIfAlreadyHas;
    public static final ForgeConfigSpec.BooleanValue hydraHeartStackPotionLevels;

    static {
        BUILDER.push("Hydra Heart Config (九头蛇心脏配置)");
        
        hydraHeartPotionThresholds = BUILDER
                .comment("九头蛇心脏药水效果的血量阈值列表 (Health percentage thresholds for potion effect)")
                .defineList("hydraHeartPotionThresholds", 
                    List.of(0.75, 0.5, 0.25), 
                    obj -> obj instanceof Double && (Double) obj >= 0.0 && (Double) obj <= 1.0);
                
        hydraHeartPotionLevels = BUILDER
                .comment("九头蛇心脏药水效果等级列表 (Potion effect levels corresponding to thresholds)")
                .defineList("hydraHeartPotionLevels", 
                    List.of(0, 1, 2, 3),
                    obj -> obj instanceof Integer && (Integer) obj >= 0);
                    
        hydraHeartPotionEffect = BUILDER
                .comment("九头蛇心脏应用的药水效果ID (Potion effect ID applied by Hydra Heart)")
                .define("hydraHeartPotionEffect", "minecraft:regeneration");
                
        hydraHeartPotionDuration = BUILDER
                .comment("九头蛇心脏药水效果的持续时间 (tick) (Potion effect duration applied by Hydra Heart (tick) )")
                .defineInRange("hydraHeartPotionDuration", 900, 1, Integer.MAX_VALUE);

        hydraHeartApplyEffectIfAlreadyHas = BUILDER
                .comment("自身已拥有对应的药水效果是否还能触发 (Whether the effect can still be triggered if the player already has the corresponding potion effect)")
                .define("hydraHeartApplyEffectIfAlreadyHas", true);

        hydraHeartStackPotionLevels = BUILDER
                .comment("自身同时持有多个九头蛇心脏是否可以叠加药水等级 (Whether potion levels can be stacked when holding multiple Hydra Hearts)")
                .define("hydraHeartStackPotionLevels", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}