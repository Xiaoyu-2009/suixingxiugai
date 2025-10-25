package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonEggConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DragonEggConfigScreen {
    
    public static List<AbstractConfigListEntry> createDragonEggConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon_egg.fireDragonEggHatchTime"),
                DragonEggConfig.fireDragonEggHatchTime,
                7200,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon_egg.iceDragonEggHatchTime"),
                DragonEggConfig.iceDragonEggHatchTime,
                7200,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon_egg.lightningDragonEggHatchTime"),
                DragonEggConfig.lightningDragonEggHatchTime,
                7200,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon_egg.iceDragonEggFreezeProbability"),
                DragonEggConfig.iceDragonEggFreezeProbability,
                500,
                1,
                Integer.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dragon_egg.lightningDragonEggSpawnLightning"),
                DragonEggConfig.lightningDragonEggSpawnLightning,
                true
        ));

        return entries;
    }
}