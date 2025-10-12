package com.xiaoyu.suixingxiugai.compat.cloth.cataclysm.item;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.cataclysm.item.MeatShredderConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeatShredderConfigScreen {
    
    public static void createMeatShredderConfigEntries(SubCategoryBuilder subCategoryBuilder, ConfigEntryBuilder entryBuilder) {
        subCategoryBuilder.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.cataclysm.item.meat_shredder_right_click_damage"),
                MeatShredderConfig.meatShredderRightClickDamage,
                1.0,
                0.0,
                Double.MAX_VALUE
        ));
    }
}