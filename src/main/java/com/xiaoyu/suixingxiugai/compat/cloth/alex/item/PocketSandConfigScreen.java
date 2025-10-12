package com.xiaoyu.suixingxiugai.compat.cloth.alex.item;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.alex.item.PocketSandConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class PocketSandConfigScreen {
    
    public static List<AbstractConfigListEntry> createPocketSandConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.pocket_sand.damage"),
                PocketSandConfig.pocketSandDamage,
                2.5,
                0.0,
                Double.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.pocket_sand.effect_level"),
                PocketSandConfig.pocketSandEffectLevel,
                0,
                0,
                Integer.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.pocket_sand.effect_id"),
                PocketSandConfig.pocketSandEffectId,
                "minecraft:blindness"
        ));
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.pocket_sand.effect_duration"),
                PocketSandConfig.pocketSandEffectDuration,
                100,
                0,
                Integer.MAX_VALUE
        ));

        return entries;
    }
}