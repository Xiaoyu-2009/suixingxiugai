package com.xiaoyu.suixingxiugai.compat.cloth.cataclysm;

import com.xiaoyu.suixingxiugai.compat.cloth.cataclysm.item.MeatShredderConfigScreen;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CataclysmConfigScreen {
    
    public static void createCataclysmConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory cataclysmCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.cataclysm"));

        SubCategoryBuilder itemSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.cataclysm.item"));
        itemSubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder meatShredderSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.cataclysm.item.meat_shredder"));
        meatShredderSubCategoryBuilder.setExpanded(false);
        MeatShredderConfigScreen.createMeatShredderConfigEntries(meatShredderSubCategoryBuilder, entryBuilder);
        itemSubCategoryBuilder.add(meatShredderSubCategoryBuilder.build());
        
        cataclysmCategory.addEntry(itemSubCategoryBuilder.build());

        SubCategoryBuilder entitySubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.cataclysm.entity"));
        entitySubCategoryBuilder.setExpanded(false);
        cataclysmCategory.addEntry(entitySubCategoryBuilder.build());
    }
}