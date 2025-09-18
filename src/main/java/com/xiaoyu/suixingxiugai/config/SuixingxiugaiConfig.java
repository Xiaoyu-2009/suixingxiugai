package com.xiaoyu.suixingxiugai.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SuixingxiugaiConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue disableExperimentalWarning;
    public static final ForgeConfigSpec.BooleanValue enableLichShieldModification;
    public static final ForgeConfigSpec.IntValue lichShieldStrength;
    public static final ForgeConfigSpec.BooleanValue enableDragonResurrectionCommand;
    public static final ForgeConfigSpec.BooleanValue enableFortificationWandTargeting;
    public static final ForgeConfigSpec.IntValue fortificationWandShieldAmount;
    public static final ForgeConfigSpec.BooleanValue enableFortificationWandInvulnerability;
    public static final ForgeConfigSpec.IntValue fortificationWandUses;
    public static final ForgeConfigSpec.IntValue zombieWandUses;
    public static final ForgeConfigSpec.IntValue twilightWandUses;
    public static final ForgeConfigSpec.IntValue lifedrainScepterUses;
    public static final ForgeConfigSpec.IntValue nagaStunDuration;
    public static final ForgeConfigSpec.IntValue nagaStunDamageThreshold;

    static {
        BUILDER.push("Suixingxiugai Config (随性修改配置)");

        disableExperimentalWarning = BUILDER
                .comment("是否禁用实验性警告 (Whether to disable experimental warning)")
                .define("disableExperimentalWarning", true);

        BUILDER.pop();
        
        BUILDER.push("Twilight Forest (暮色森林)");
        
        enableLichShieldModification = BUILDER
                .comment("巫妖的护盾可被更多伤害破坏 (Whether Lich's shield can be broken by more types of damage)")
                .define("enableLichShieldModification", false);
                
        lichShieldStrength = BUILDER
                .comment("巫妖的护盾数量 (Number of Lich's shields)")
                .defineInRange("lichShieldStrength", 6, 1, Integer.MAX_VALUE);
                
        enableFortificationWandTargeting = BUILDER
                .comment("护盾权杖目标选择功能 (Enable target selection for fortification wand)")
                .define("enableFortificationWandTargeting", false);
                
        fortificationWandShieldAmount = BUILDER
                .comment("护盾权杖单次施加的护盾数量 (Number of shields granted by fortification wand per use)")
                .defineInRange("fortificationWandShieldAmount", 5, 1, Integer.MAX_VALUE);

        enableFortificationWandInvulnerability = BUILDER
                .comment("护盾权杖施加的护盾免疫一切伤害 (Entities with fortification wand shields are invulnerable to all damage)")
                .define("enableFortificationWandInvulnerability", false);

        fortificationWandUses = BUILDER
                .comment("护盾权杖使用次数 (Number of uses for fortification wand)")
                .defineInRange("fortificationWandUses", 9, 1, Integer.MAX_VALUE);
                
        zombieWandUses = BUILDER
                .comment("僵尸权杖使用次数 (Number of uses for zombie wand)")
                .defineInRange("zombieWandUses", 9, 1, Integer.MAX_VALUE);
                
        twilightWandUses = BUILDER
                .comment("黄昏权杖使用次数 (Number of uses for twilight wand)")
                .defineInRange("twilightWandUses", 99, 1, Integer.MAX_VALUE);
                
        lifedrainScepterUses = BUILDER
                .comment("吸血权杖使用次数 (Number of uses for lifedrain scepter)")
                .defineInRange("lifedrainScepterUses", 99, 1, Integer.MAX_VALUE);

        nagaStunDuration = BUILDER
                .comment("娜迦眩晕持续时间 (Naga stun duration)")
                .defineInRange("nagaStunDuration", 60, 1, Integer.MAX_VALUE);
                
        nagaStunDamageThreshold = BUILDER
                .comment("娜迦眩晕伤害阈值 (Naga stun damage threshold)")
                .defineInRange("nagaStunDamageThreshold", 15, 1, Integer.MAX_VALUE);
                
        BUILDER.pop();
        
        BUILDER.push("Ice and Fire (冰火传说)");
        
        enableDragonResurrectionCommand = BUILDER
                .comment("启用龙复活指令 (Enable dragon resurrection command)")
                .define("enableDragonResurrectionCommand", true);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}