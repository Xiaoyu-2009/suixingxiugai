package com.xiaoyu.suixingxiugai.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SuixingxiugaiConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue disableExperimentalWarning;
    public static final ForgeConfigSpec.BooleanValue enableLichShieldModification;
    public static final ForgeConfigSpec.BooleanValue enableDragonResurrectionCommand;

    static {
        BUILDER.push("Suixingxiugai Config (随性修改配置)");

        disableExperimentalWarning = BUILDER
                .comment("是否禁用实验性警告 (Whether to disable experimental warning)")
                .define("disableExperimentalWarning", true);

        BUILDER.pop();
        
        BUILDER.push("Twilight Forest (暮色森林)");
        
        enableLichShieldModification = BUILDER
                .comment("巫妖法术护盾可被更多伤害破坏 (Whether Lich's spell shield can be destroyed by more types of damage)")
                .define("enableLichShieldModification", false);
                
        BUILDER.pop();
        
        BUILDER.push("Ice and Fire (冰火传说)");
        
        enableDragonResurrectionCommand = BUILDER
                .comment("启用龙复活指令 (Enable dragon resurrection command)")
                .define("enableDragonResurrectionCommand", true);
                
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}