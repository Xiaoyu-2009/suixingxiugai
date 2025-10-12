package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity.CyclopsConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity.DeathWormConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity.DragonConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity.DragonEggConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity.DreadMobConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity.HydraConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity.SirenConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity.GorgonConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item.CyclopsEyeConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item.DeathwormGauntletConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item.GorgonHeadConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item.HydraHeartConfigScreen;
import com.xiaoyu.suixingxiugai.config.iceandfire.GazeImmunityConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.IceandfireConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class IceAndFireConfigScreen {
    
    public static void createIceAndFireConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory iceAndFireCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.iceandfire"));

        SubCategoryBuilder generalSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.general"));
        generalSubCategoryBuilder.setExpanded(false);
        generalSubCategoryBuilder.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.enableStatueRestoreCommand"),
                IceandfireConfig.enableStatueRestoreCommand,
                true
        ));
        iceAndFireCategory.addEntry(generalSubCategoryBuilder.build());

        SubCategoryBuilder gazeImmunitySubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.gaze_immunity"));
        gazeImmunitySubCategoryBuilder.setExpanded(false);
        List<String> gazeImmunityArmorList = new ArrayList<>(GazeImmunityConfig.gazeImmunityArmorList.get());
        gazeImmunitySubCategoryBuilder.add(ConfigEntryHelper.createStringList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.gazeImmunityArmorList"),
                GazeImmunityConfig.gazeImmunityArmorList,
                new ArrayList<>()
        ));
                
        List<String> gazeImmunityItemList = new ArrayList<>(GazeImmunityConfig.gazeImmunityItemList.get());
        gazeImmunitySubCategoryBuilder.add(ConfigEntryHelper.createStringList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.gazeImmunityItemList"),
                GazeImmunityConfig.gazeImmunityItemList,
                new ArrayList<>()
        ));
                
        List<String> gazeImmunityCuriosList = new ArrayList<>(GazeImmunityConfig.gazeImmunityCuriosList.get());
        gazeImmunitySubCategoryBuilder.add(ConfigEntryHelper.createStringList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.gazeImmunityCuriosList"),
                GazeImmunityConfig.gazeImmunityCuriosList,
                new ArrayList<>()
        ));
        iceAndFireCategory.addEntry(gazeImmunitySubCategoryBuilder.build());

        SubCategoryBuilder entitySubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.entity"));
        entitySubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder cyclopsSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.entity.cyclops"));
        cyclopsSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> cyclopsEntries = CyclopsConfigScreen.createCyclopsConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : cyclopsEntries) {
            cyclopsSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(cyclopsSubCategoryBuilder.build());

        SubCategoryBuilder deathWormSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.entity.deathworm"));
        deathWormSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> deathWormEntries = DeathWormConfigScreen.createDeathWormConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : deathWormEntries) {
            deathWormSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(deathWormSubCategoryBuilder.build());

        SubCategoryBuilder dragonSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.entity.dragon"));
        dragonSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> dragonEntries = DragonConfigScreen.createDragonConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : dragonEntries) {
            dragonSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(dragonSubCategoryBuilder.build());

        SubCategoryBuilder dragonEggSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.entity.dragon_egg"));
        dragonEggSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> dragonEggEntries = DragonEggConfigScreen.createDragonEggConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : dragonEggEntries) {
            dragonEggSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(dragonEggSubCategoryBuilder.build());
        
        SubCategoryBuilder dreadMobSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob"));
        dreadMobSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> dreadMobEntries = DreadMobConfigScreen.createDreadMobConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : dreadMobEntries) {
            dreadMobSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(dreadMobSubCategoryBuilder.build());

        SubCategoryBuilder hydraSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.entity.hydra"));
        hydraSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> hydraEntries = HydraConfigScreen.createHydraConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : hydraEntries) {
            hydraSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(hydraSubCategoryBuilder.build());
        
        SubCategoryBuilder sirenSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.entity.siren"));
        sirenSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> sirenEntries = SirenConfigScreen.createSirenConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : sirenEntries) {
            sirenSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(sirenSubCategoryBuilder.build());

        SubCategoryBuilder gorgonSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.entity.gorgon"));
        gorgonSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> gorgonEntries = GorgonConfigScreen.createGorgonConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : gorgonEntries) {
            gorgonSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(gorgonSubCategoryBuilder.build());
        
        iceAndFireCategory.addEntry(entitySubCategoryBuilder.build());

        SubCategoryBuilder dragonDenSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.dragonden"));
        dragonDenSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> dragonDenEntries = DragonDenConfigScreen.createDragonDenConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : dragonDenEntries) {
            dragonDenSubCategoryBuilder.add(entry);
        }
        iceAndFireCategory.addEntry(dragonDenSubCategoryBuilder.build());

        SubCategoryBuilder itemSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.item"));
        itemSubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder cyclopsEyeSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye"));
        cyclopsEyeSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> cyclopsEyeEntries = CyclopsEyeConfigScreen.createCyclopsEyeConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : cyclopsEyeEntries) {
            cyclopsEyeSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(cyclopsEyeSubCategoryBuilder.build());

        SubCategoryBuilder deathwormGauntletSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet"));
        deathwormGauntletSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> deathwormGauntletEntries = DeathwormGauntletConfigScreen.createDeathwormGauntletConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : deathwormGauntletEntries) {
            deathwormGauntletSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(deathwormGauntletSubCategoryBuilder.build());

        SubCategoryBuilder hydraHeartSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart"));
        hydraHeartSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> hydraHeartEntries = HydraHeartConfigScreen.createHydraHeartConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : hydraHeartEntries) {
            hydraHeartSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(hydraHeartSubCategoryBuilder.build());
        
        SubCategoryBuilder gorgonHeadSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.iceandfire.item.gorgon_head"));
        gorgonHeadSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> gorgonHeadEntries = GorgonHeadConfigScreen.createGorgonHeadConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : gorgonHeadEntries) {
            gorgonHeadSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(gorgonHeadSubCategoryBuilder.build());
        
        iceAndFireCategory.addEntry(itemSubCategoryBuilder.build());
    }
}