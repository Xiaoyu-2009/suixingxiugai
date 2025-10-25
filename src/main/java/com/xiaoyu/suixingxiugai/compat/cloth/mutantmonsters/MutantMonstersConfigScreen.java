package com.xiaoyu.suixingxiugai.compat.cloth.mutantmonsters;

import com.xiaoyu.suixingxiugai.compat.cloth.mutantmonsters.entity.MutantZombieConfigScreen;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class MutantMonstersConfigScreen {
    
    public static void createMutantMonstersConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory mutantMonstersCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.mutantmonsters"));

        SubCategoryBuilder entitySubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.mutantmonsters.entity"));
        entitySubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder mutantZombieSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.mutantmonsters.mutant_zombie"));
        mutantZombieSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> mutantZombieEntries = MutantZombieConfigScreen.createMutantZombieConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : mutantZombieEntries) {
            mutantZombieSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(mutantZombieSubCategoryBuilder.build());
        
        mutantMonstersCategory.addEntry(entitySubCategoryBuilder.build());

        SubCategoryBuilder itemSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.mutantmonsters.item"));
        itemSubCategoryBuilder.setExpanded(false);
        mutantMonstersCategory.addEntry(itemSubCategoryBuilder.build());
    }
}