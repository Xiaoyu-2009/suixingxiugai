package com.xiaoyu.suixingxiugai.config.iceandfire.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class DreadMobConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue dreadMobsAttackAllMobs;
    public static final ForgeConfigSpec.BooleanValue dreadLichAttackMobs;
    public static final ForgeConfigSpec.BooleanValue dreadThrallAttackMobs;
    public static final ForgeConfigSpec.BooleanValue dreadBeastAttackMobs;
    public static final ForgeConfigSpec.BooleanValue dreadGhoulAttackMobs;
    public static final ForgeConfigSpec.BooleanValue dreadScuttlerAttackMobs;
    public static final ForgeConfigSpec.BooleanValue dreadKnightAttackMobs;
    public static final ForgeConfigSpec.BooleanValue dreadKnightHorseAttackMobs;

    static {
        BUILDER.push("Dread Mob Config (悚怖生物配置)");

        dreadMobsAttackAllMobs = BUILDER
                .comment("悚怖所有生物是否无差别攻击其他生物 - 最高优先级",
                "Whether all dread mobs attack other mobs unconditionally - Highest priority")
                .define("dreadMobsAttackAllMobs", true);
                
        dreadLichAttackMobs = BUILDER
                .comment("悚怖尸巫是否无差别攻击其他生物 - 次要优先级",
                "Whether Dread Lich attacks other mobs unconditionally - Secondary priority")
                .define("dreadLichAttackMobs", true);
                
        dreadThrallAttackMobs = BUILDER
                .comment("悚怖尸奴是否无差别攻击其他生物 - 次要优先级",
                "Whether Dread Thrall attacks other mobs unconditionally - Secondary priority")
                .define("dreadThrallAttackMobs", true);
                
        dreadBeastAttackMobs = BUILDER
                .comment("悚怖尸兽是否无差别攻击其他生物 - 次要优先级",
                "Whether Dread Beast attacks other mobs unconditionally - Secondary priority")
                .define("dreadBeastAttackMobs", true);
                
        dreadGhoulAttackMobs = BUILDER
                .comment("悚怖食尸鬼是否无差别攻击其他生物 - 次要优先级",
                "Whether Dread Ghoul attacks other mobs unconditionally - Secondary priority")
                .define("dreadGhoulAttackMobs", true);
                
        dreadScuttlerAttackMobs = BUILDER
                .comment("悚怖劫蛛是否无差别攻击其他生物 - 次要优先级",
                "Whether Dread Scuttler attacks other mobs unconditionally - Secondary priority")
                .define("dreadScuttlerAttackMobs", true);
                
        dreadKnightAttackMobs = BUILDER
                .comment("悚怖尸骑是否无差别攻击其他生物 - 次要优先级",
                "Whether Dread Knight attacks other mobs unconditionally - Secondary priority")
                .define("dreadKnightAttackMobs", true);
                
        dreadKnightHorseAttackMobs = BUILDER
                .comment("悚怖尸骑战马是否无差别攻击其他生物 - 次要优先级",
                "Whether Dread Knight Horse attacks other mobs unconditionally - Secondary priority")
                .define("dreadKnightHorseAttackMobs", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}