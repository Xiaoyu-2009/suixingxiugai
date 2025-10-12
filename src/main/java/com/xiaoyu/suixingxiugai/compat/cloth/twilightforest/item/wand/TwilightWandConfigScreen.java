package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item.wand;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.TwilightWandConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class TwilightWandConfigScreen {
    
    public static void createTwilightWandConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory twilightWandCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.wand.twilight"));
        
        List<AbstractConfigListEntry> entries = createTwilightWandConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            twilightWandCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createTwilightWandConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.twilight.twilight_uses"),
                TwilightWandConfig.twilightWandUses,
                99,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.twilight.twilight_cooldown"),
                TwilightWandConfig.twilightWandCooldown,
                0,
                0,
                Integer.MAX_VALUE
        ));

        return entries;
    }
}