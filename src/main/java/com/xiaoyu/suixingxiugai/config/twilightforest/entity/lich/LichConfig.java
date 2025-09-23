package com.xiaoyu.suixingxiugai.config.twilightforest.entity.lich;

import net.minecraftforge.common.ForgeConfigSpec;

public class LichConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue enableLichShieldModification;
    public static final ForgeConfigSpec.IntValue lichShieldStrength;

    static {
        BUILDER.push("TwilightForest (暮色森林)");
        BUILDER.push("Lich Config (巫妖配置)");
        
        enableLichShieldModification = BUILDER
                .comment("巫妖的护盾可被更多伤害破坏 (Whether Lich's shield can be broken by more types of damage)")
                .define("enableLichShieldModification", false);
                
        lichShieldStrength = BUILDER
                .comment("巫妖的护盾数量 (Number of Lich's shields)")
                .defineInRange("lichShieldStrength", 6, 1, Integer.MAX_VALUE);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}