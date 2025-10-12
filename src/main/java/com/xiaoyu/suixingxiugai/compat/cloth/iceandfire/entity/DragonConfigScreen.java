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

        return entries;
    }
}