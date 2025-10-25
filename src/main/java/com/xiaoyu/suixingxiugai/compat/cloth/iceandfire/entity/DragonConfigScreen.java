package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder; 

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DragonConfigScreen {
    
    public static List<AbstractConfigListEntry> createDragonConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.enableDragonResurrectionCommand"),
                DragonConfig.enableDragonResurrectionCommand,
                true
        ));
                
        /* entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.wildDragonDefaultSleeping"),
                DragonConfig.wildDragonDefaultSleeping,
                true
        )); */
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.wildDragonDefaultSleepingSurface"),
                DragonConfig.wildDragonDefaultSleepingSurface,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.wildDragonDefaultSleepingUnderground"),
                DragonConfig.wildDragonDefaultSleepingUnderground,
                true
        ));

        List<AbstractConfigListEntry> fireDragonEntries = new ArrayList<>();
        
        fireDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.fireDragonSleeping"),
                DragonConfig.fireDragonSleeping,
                true
        ));
        
        fireDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.fireDragonDestroyBlocks"),
                DragonConfig.fireDragonDestroyBlocks,
                true
        ));
        
        fireDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.tamedFireDragonDestroyBlocks"),
                DragonConfig.tamedFireDragonDestroyBlocks,
                true
        ));
        
        fireDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.fireDragonBreathDestroyBlocks"),
                DragonConfig.fireDragonBreathDestroyBlocks,
                true
        ));
        
        fireDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.tamedFireDragonBreathDestroyBlocks"),
                DragonConfig.tamedFireDragonBreathDestroyBlocks,
                true
        ));
        
        fireDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.fireDragonCanBreath"),
                DragonConfig.fireDragonCanBreath,
                true
        ));
        
        fireDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.tamedFireDragonCanBreath"),
                DragonConfig.tamedFireDragonCanBreath,
                true
        ));
        
        entries.add(entryBuilder.startSubCategory(
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.fireDragonCategory"),
                fireDragonEntries
        ).build());

        List<AbstractConfigListEntry> iceDragonEntries = new ArrayList<>();
        
        iceDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.iceDragonSleeping"),
                DragonConfig.iceDragonSleeping,
                true
        ));
        
        iceDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.iceDragonDestroyBlocks"),
                DragonConfig.iceDragonDestroyBlocks,
                true
        ));
        
        iceDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.tamedIceDragonDestroyBlocks"),
                DragonConfig.tamedIceDragonDestroyBlocks,
                true
        ));
        
        iceDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.iceDragonBreathDestroyBlocks"),
                DragonConfig.iceDragonBreathDestroyBlocks,
                true
        ));
        
        iceDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.tamedIceDragonBreathDestroyBlocks"),
                DragonConfig.tamedIceDragonBreathDestroyBlocks,
                true
        ));
        
        iceDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.iceDragonCanBreath"),
                DragonConfig.iceDragonCanBreath,
                true
        ));
        
        iceDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.tamedIceDragonCanBreath"),
                DragonConfig.tamedIceDragonCanBreath,
                true
        ));
        
        entries.add(entryBuilder.startSubCategory(
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.iceDragonCategory"),
                iceDragonEntries
        ).build());

        List<AbstractConfigListEntry> lightningDragonEntries = new ArrayList<>();
        
        lightningDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.lightningDragonSleeping"),
                DragonConfig.lightningDragonSleeping,
                true
        ));
        
        lightningDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.lightningDragonDestroyBlocks"),
                DragonConfig.lightningDragonDestroyBlocks,
                true
        ));
        
        lightningDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.tamedLightningDragonDestroyBlocks"),
                DragonConfig.tamedLightningDragonDestroyBlocks,
                true
        ));
        
        lightningDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.lightningDragonBreathDestroyBlocks"),
                DragonConfig.lightningDragonBreathDestroyBlocks,
                true
        ));
        
        lightningDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.tamedLightningDragonBreathDestroyBlocks"),
                DragonConfig.tamedLightningDragonBreathDestroyBlocks,
                true
        ));
        
        lightningDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.lightningDragonCanBreath"),
                DragonConfig.lightningDragonCanBreath,
                true
        ));
        
        lightningDragonEntries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.tamedLightningDragonCanBreath"),
                DragonConfig.tamedLightningDragonCanBreath,
                true
        ));
        
        entries.add(entryBuilder.startSubCategory(
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.lightningDragonCategory"),
                lightningDragonEntries
        ).build());

        return entries;
    }
}