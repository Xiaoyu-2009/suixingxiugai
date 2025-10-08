package com.xiaoyu.suixingxiugai.config.iceandfire.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class CyclopsConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.DoubleValue cyclopsBiteStrength;
    public static final ForgeConfigSpec.BooleanValue cyclopsCanStompAttack;
    public static final ForgeConfigSpec.BooleanValue cyclopsCanGrabAttack;
    public static final ForgeConfigSpec.BooleanValue cyclopsCanKickAttack;

    static {
        BUILDER.push("Cyclops Config (独眼巨人配置)");
        
        cyclopsBiteStrength = BUILDER
                .comment("独眼巨人抓起攻击啃食目标造成的伤害 (Amount of damage done with cyclops bite attack when grabbing and eating a target)")
                .defineInRange("cyclopsBiteStrength", 40.0, 0.0, Double.MAX_VALUE);
                
        cyclopsCanStompAttack = BUILDER
                .comment("独眼巨人是否能踩踏攻击 (Whether cyclops can perform stomp attacks)")
                .define("cyclopsCanStompAttack", true);
                
        cyclopsCanGrabAttack = BUILDER
                .comment("独眼巨人是否能抓取攻击 (Whether cyclops can perform grab attacks)")
                .define("cyclopsCanGrabAttack", true);
                
        cyclopsCanKickAttack = BUILDER
                .comment("独眼巨人是否能踢击攻击 (Whether cyclops can perform kick attacks)")
                .define("cyclopsCanKickAttack", true);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}