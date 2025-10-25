package com.xiaoyu.suixingxiugai.config.iceandfire.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class DragonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue enableDragonResurrectionCommand;
    public static final ForgeConfigSpec.BooleanValue wildDragonDefaultSleepingSurface;
    public static final ForgeConfigSpec.BooleanValue wildDragonDefaultSleepingUnderground;
    public static final ForgeConfigSpec.BooleanValue fireDragonSleeping;
    public static final ForgeConfigSpec.BooleanValue fireDragonDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue tamedFireDragonDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue fireDragonBreathDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue tamedFireDragonBreathDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue fireDragonCanBreath;
    public static final ForgeConfigSpec.BooleanValue tamedFireDragonCanBreath;
    public static final ForgeConfigSpec.BooleanValue iceDragonSleeping;
    public static final ForgeConfigSpec.BooleanValue iceDragonDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue tamedIceDragonDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue iceDragonBreathDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue tamedIceDragonBreathDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue iceDragonCanBreath;
    public static final ForgeConfigSpec.BooleanValue tamedIceDragonCanBreath;
    public static final ForgeConfigSpec.BooleanValue lightningDragonSleeping;
    public static final ForgeConfigSpec.BooleanValue lightningDragonDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue tamedLightningDragonDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue lightningDragonBreathDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue tamedLightningDragonBreathDestroyBlocks;
    public static final ForgeConfigSpec.BooleanValue lightningDragonCanBreath;
    public static final ForgeConfigSpec.BooleanValue tamedLightningDragonCanBreath;

    static {
        BUILDER.push("Dragon Config (龙配置)");
        
        enableDragonResurrectionCommand = BUILDER
                .comment("启用龙复活指令 (Enable dragon resurrection command)")
                .define("enableDragonResurrectionCommand", true);
                
        wildDragonDefaultSleepingSurface = BUILDER
                .comment("地表生成的野生龙默认是否处于睡眠状态",
                "Whether wild dragons generated on the surface default to sleeping state")
                .define("wildDragonDefaultSleepingSurface", true);
                
        wildDragonDefaultSleepingUnderground = BUILDER
                .comment("地下龙巢生成的野生龙默认是否处于睡眠状态",
                "Whether wild dragons generated in underground dens default to sleeping state")
                .define("wildDragonDefaultSleepingUnderground", true);

        BUILDER.push("Fire Dragon Config (火龙配置)");
                
        fireDragonSleeping = BUILDER
                .comment("火龙是否进入睡眠状态 (Whether fire dragons will enter sleep state)")
                .define("fireDragonSleeping", true);
                
        fireDragonDestroyBlocks = BUILDER
                .comment("火龙是否会破坏方块 (Whether fire dragons can destroy blocks)")
                .define("fireDragonDestroyBlocks", true);
                
        tamedFireDragonDestroyBlocks = BUILDER
                .comment("已驯服的火龙是否会破坏方块 (Whether tamed fire dragons can destroy blocks)")
                .define("tamedFireDragonDestroyBlocks", true);
                
        fireDragonBreathDestroyBlocks = BUILDER
                .comment("火龙的龙息是否会破坏方块 (Whether fire dragon breath can destroy blocks)")
                .define("fireDragonBreathDestroyBlocks", true);
                
        tamedFireDragonBreathDestroyBlocks = BUILDER
                .comment("已驯服的火龙的龙息是否会破坏方块 (Whether tamed fire dragon breath can destroy blocks)")
                .define("tamedFireDragonBreathDestroyBlocks", true);
                
        fireDragonCanBreath = BUILDER
                .comment("火龙是否能够吐龙息 (Whether fire dragons can breath fire)")
                .define("fireDragonCanBreath", true);
                
        tamedFireDragonCanBreath = BUILDER
                .comment("已驯服的火龙是否能够吐龙息 (Whether tamed fire dragons can breath fire)")
                .define("tamedFireDragonCanBreath", true);
                
        BUILDER.pop();

        BUILDER.push("Ice Dragon Config (冰龙配置)");
                
        iceDragonSleeping = BUILDER
                .comment("冰龙是否进入睡眠状态 (Whether ice dragons will enter sleep state)")
                .define("iceDragonSleeping", true);
                
        iceDragonDestroyBlocks = BUILDER
                .comment("冰龙是否会破坏方块 (Whether ice dragons can destroy blocks)")
                .define("iceDragonDestroyBlocks", true);
                
        tamedIceDragonDestroyBlocks = BUILDER
                .comment("已驯服的冰龙是否会破坏方块 (Whether tamed ice dragons can destroy blocks)")
                .define("tamedIceDragonDestroyBlocks", true);
                
        iceDragonBreathDestroyBlocks = BUILDER
                .comment("冰龙的龙息是否会破坏方块 (Whether ice dragon breath can destroy blocks)")
                .define("iceDragonBreathDestroyBlocks", true);
                
        tamedIceDragonBreathDestroyBlocks = BUILDER
                .comment("已驯服的冰龙的龙息是否会破坏方块 (Whether tamed ice dragon breath can destroy blocks)")
                .define("tamedIceDragonBreathDestroyBlocks", true);
                
        iceDragonCanBreath = BUILDER
                .comment("冰龙是否能够吐龙息 (Whether ice dragons can breath ice)")
                .define("iceDragonCanBreath", true);
                
        tamedIceDragonCanBreath = BUILDER
                .comment("已驯服的冰龙是否能够吐龙息 (Whether tamed ice dragons can breath ice)")
                .define("tamedIceDragonCanBreath", true);
                
        BUILDER.pop();

        BUILDER.push("Lightning Dragon Config (电龙配置)");
                
        lightningDragonSleeping = BUILDER
                .comment("电龙是否进入睡眠状态 (Whether lightning dragons will enter sleep state)")
                .define("lightningDragonSleeping", true);
                
        lightningDragonDestroyBlocks = BUILDER
                .comment("电龙是否会破坏方块 (Whether lightning dragons can destroy blocks)")
                .define("lightningDragonDestroyBlocks", true);
                
        tamedLightningDragonDestroyBlocks = BUILDER
                .comment("已驯服的电龙是否会破坏方块 (Whether tamed lightning dragons can destroy blocks)")
                .define("tamedLightningDragonDestroyBlocks", true);
                
        lightningDragonBreathDestroyBlocks = BUILDER
                .comment("电龙的龙息是否会破坏方块 (Whether lightning dragon breath can destroy blocks)")
                .define("lightningDragonBreathDestroyBlocks", true);
                
        tamedLightningDragonBreathDestroyBlocks = BUILDER
                .comment("已驯服的电龙的龙息是否会破坏方块 (Whether tamed lightning dragon breath can destroy blocks)")
                .define("tamedLightningDragonBreathDestroyBlocks", true);
                
        lightningDragonCanBreath = BUILDER
                .comment("电龙是否能够吐龙息 (Whether lightning dragons can breath lightning)")
                .define("lightningDragonCanBreath", true);
                
        tamedLightningDragonCanBreath = BUILDER
                .comment("已驯服的电龙是否能够吐龙息 (Whether tamed lightning dragons can breath lightning)")
                .define("tamedLightningDragonCanBreath", true);
                
        BUILDER.pop();
        
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}