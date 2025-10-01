package com.xiaoyu.suixingxiugai.config.iceandfire;

import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class GazeImmunityConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> gazeImmunityArmorList;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> gazeImmunityItemList;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> gazeImmunityCuriosList;

    static {
        BUILDER.push("Dragon Config (免疫凝视配置)");

        gazeImmunityArmorList = BUILDER
                .comment("免疫凝视装备列表 (Gaze Immunity Armor List)",
                "列表中的物品在头盔，胸甲，护腿，靴子则免疫凝视",
                "Items in this list provide immunity when worn as helmet, chestplate, leggings, or boots")
                .defineList("gazeImmunityArmorList", 
                        List.of(),
                        obj -> obj instanceof String);
                        
        gazeImmunityItemList = BUILDER
                .comment("免疫凝视背包列表 (Gaze Immunity Inventory List)",
                "列表中的物品在物品栏，快捷栏，主/副手则免疫凝视",
                "Items in this list provide immunity when carried in inventory, hotbar, main hand, or off hand")
                .defineList("gazeImmunityItemList", 
                        List.of(),
                        obj -> obj instanceof String);
                        
        gazeImmunityCuriosList = BUILDER
                .comment("免疫凝视饰品列表 (Gaze Immunity Curios List)",
                "列表中填写的饰品会免疫凝视",
                "Items in the list provide immunity when equipped as curios")
                .defineList("gazeImmunityCuriosList", 
                        List.of(),
                        obj -> obj instanceof String);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}