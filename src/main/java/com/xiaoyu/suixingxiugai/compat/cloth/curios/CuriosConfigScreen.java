package com.xiaoyu.suixingxiugai.compat.cloth.curios;

import com.xiaoyu.suixingxiugai.compat.cloth.curios.iceandfire.entity.CyclopsEyeConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.curios.iceandfire.item.HydraHeartConfigScreen;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CuriosConfigScreen {
    
    public static void createCuriosConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory curiosCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.curios"));

        SubCategoryBuilder iceAndFireSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.curios.iceandfire"));
        iceAndFireSubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder entitySubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.curios.iceandfire.entity"));
        entitySubCategoryBuilder.setExpanded(false);
        iceAndFireSubCategoryBuilder.add(entitySubCategoryBuilder.build());

        SubCategoryBuilder itemSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.curios.iceandfire.item"));
        itemSubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder cyclopsEyeSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.curios.iceandfire.item.cyclops_eye"));
        cyclopsEyeSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> cyclopsEyeEntries = CyclopsEyeConfigScreen.createCyclopsEyeConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : cyclopsEyeEntries) {
            cyclopsEyeSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(cyclopsEyeSubCategoryBuilder.build());

        SubCategoryBuilder hydraHeartSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.curios.iceandfire.item.hydra_heart"));
        hydraHeartSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> hydraHeartEntries = HydraHeartConfigScreen.createHydraHeartConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : hydraHeartEntries) {
            hydraHeartSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(hydraHeartSubCategoryBuilder.build());
        iceAndFireSubCategoryBuilder.add(itemSubCategoryBuilder.build());
        
        curiosCategory.addEntry(iceAndFireSubCategoryBuilder.build());
    }
}