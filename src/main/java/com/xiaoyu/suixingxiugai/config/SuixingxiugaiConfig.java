package com.xiaoyu.suixingxiugai.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class SuixingxiugaiConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue disableExperimentalWarning;
    public static final ForgeConfigSpec.BooleanValue enableDamageRandomization;
    public static final ForgeConfigSpec.ConfigValue<String> damageRandomizationEntityTypes;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> damageRandomizationEntities;
    public static final ForgeConfigSpec.DoubleValue damageRandomizationMinMultiplier;
    public static final ForgeConfigSpec.DoubleValue damageRandomizationMaxMultiplier;
    public static final ForgeConfigSpec.BooleanValue enableDamageNumberDisplay;
    public static final ForgeConfigSpec.ConfigValue<String> damageNumberDisplayEntityTypes;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> damageNumberDisplayEntities;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> damageNumberDisplayBlacklistEntities;
    public static final ForgeConfigSpec.ConfigValue<String> damageNumberColor;
    public static final ForgeConfigSpec.ConfigValue<String> critDamageNumberColor;
    public static final ForgeConfigSpec.BooleanValue enableKeepInventoryOnEnterWorld;

    static {
        BUILDER.push("Suixingxiugai Config (随性修改配置)");

        disableExperimentalWarning = BUILDER
                .comment("是否禁用实验性警告 (Whether to disable experimental warning)")
                .define("disableExperimentalWarning", true);
        
        enableDamageRandomization = BUILDER
                .comment("是否启用伤害随机化 (Whether to enable damage randomization)")
                .define("enableDamageRandomization", false);

        damageRandomizationEntityTypes = BUILDER
                .comment("伤害随机化适用的实体类型 (可选: mob, living, player, all, animal, monster, ambient, water_animal, flying)",
                "(Entity types for damage randomization) (options: mob, living, player, all, animal, monster, ambient, water_animal, flying)")
                .define("damageRandomizationEntityTypes", "living");

        damageRandomizationEntities = BUILDER
                .comment("伤害随机化适用的具体实体列表 (Specific entities for damage randomization)")
                .defineList("damageRandomizationEntities", 
                        new ArrayList<>(), 
                        obj -> obj instanceof String && !((String) obj).isEmpty());

        damageRandomizationMinMultiplier = BUILDER
                .comment("伤害随机化的最小倍数 (Minimum damage multiplier for randomization)")
                .defineInRange("damageRandomizationMinMultiplier", 0.0, 0.0, Double.MAX_VALUE);
        
        damageRandomizationMaxMultiplier = BUILDER
                .comment("伤害随机化的最大倍数 (Maximum damage multiplier for randomization)")
                .defineInRange("damageRandomizationMaxMultiplier", 5.0, 0.0, Double.MAX_VALUE);

        enableDamageNumberDisplay = BUILDER
                .comment("是否启用伤害数字显示 (Whether to enable damage number display)")
                .define("enableDamageNumberDisplay", false);

        damageNumberDisplayEntityTypes = BUILDER
                .comment("伤害数字显示适用的实体类型 (可选: mob, living, player, all, animal, monster, ambient, water_animal, flying)",
                "(Entity types for damage number display) (options: mob, living, player, all, animal, monster, ambient, water_animal, flying)")
                .define("damageNumberDisplayEntityTypes", "living");

        damageNumberDisplayEntities = BUILDER
                .comment("伤害数字显示适用的具体实体列表 (Specific entities for damage number display)")
                .defineList("damageNumberDisplayEntities", 
                        new ArrayList<>(), 
                        obj -> obj instanceof String && !((String) obj).isEmpty());

        damageNumberDisplayBlacklistEntities = BUILDER
                .comment("伤害数字显示的黑名单实体列表 (Blacklist entities for damage number display)")
                .defineList("damageNumberDisplayBlacklistEntities", 
                        List.of("dummmmmmy:target_dummy"), 
                        obj -> obj instanceof String && !((String) obj).isEmpty());

        damageNumberColor = BUILDER
                .comment("伤害数字显示的颜色 (十六进制颜色代码)",
                "(Damage number display color) (Hex color code)")
                .define("damageNumberColor", "#FFFFFF");

        critDamageNumberColor = BUILDER
                .comment("暴击伤害数字显示的颜色 (十六进制颜色代码)",
                "(Critical damage number display color) (Hex color code)")
                .define("critDamageNumberColor", "#FF0000");

        enableKeepInventoryOnEnterWorld = BUILDER
                .comment("是否启用进入存档自动开启死亡不掉落 (Whether to enable keep inventory on enter world)")
                .define("enableKeepInventoryOnEnterWorld", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}