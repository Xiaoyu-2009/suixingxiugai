package com.xiaoyu.suixingxiugai.compat.cloth.bosses_of_mass_destruction;

import com.xiaoyu.suixingxiugai.compat.cloth.bosses_of_mass_destruction.entity.GauntletConfigScreen;

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
public class BossesOfMassDestructionConfigScreen {
    
    public static void createBossesOfMassDestructionConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory bossesOfMassDestructionCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.bosses_of_mass_destruction"));

        SubCategoryBuilder entitySubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.bosses_of_mass_destruction.entity"));
        entitySubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder gauntletSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.bosses_of_mass_destruction.entity.gauntlet"));
        gauntletSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> gauntletEntries = GauntletConfigScreen.createGauntletConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : gauntletEntries) {
            gauntletSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(gauntletSubCategoryBuilder.build());
        
        bossesOfMassDestructionCategory.addEntry(entitySubCategoryBuilder.build());

        SubCategoryBuilder itemSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.bosses_of_mass_destruction.item"));
        itemSubCategoryBuilder.setExpanded(false);
        bossesOfMassDestructionCategory.addEntry(itemSubCategoryBuilder.build());
    }
}