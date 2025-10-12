package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item.wand;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.LifedrainScepterConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class LifedrainScepterConfigScreen {
    
    public static void createLifedrainScepterConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory lifedrainScepterCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.wand.lifedrain"));
        
        List<AbstractConfigListEntry> entries = createLifedrainScepterConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            lifedrainScepterCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createLifedrainScepterConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.lifedrain.lifedrain_uses"),
                LifedrainScepterConfig.lifedrainScepterUses,
                99,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.lifedrain.lifedrain_cooldown"),
                LifedrainScepterConfig.lifedrainScepterCooldown,
                0,
                0,
                Integer.MAX_VALUE
        ));

        return entries;
    }
}