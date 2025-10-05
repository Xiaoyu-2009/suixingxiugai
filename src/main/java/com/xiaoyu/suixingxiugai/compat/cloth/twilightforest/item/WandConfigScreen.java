package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class WandConfigScreen {
    
    public static void createWandConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        
        ConfigCategory wandCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.wand"));
        
        List<AbstractConfigListEntry> entries = createWandConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            wandCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createWandConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.enable_fortification_targeting"),
                WandConfig.enableFortificationWandTargeting,
                false
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.fortification_shield_amount"),
                WandConfig.fortificationWandShieldAmount,
                5,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.enable_fortification_invulnerability"),
                WandConfig.enableFortificationWandInvulnerability,
                false
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.fortification_uses"),
                WandConfig.fortificationWandUses,
                9,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.zombie_uses"),
                WandConfig.zombieWandUses,
                9,
                1,
                Integer.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.twilight_uses"),
                WandConfig.twilightWandUses,
                99,
                1,
                Integer.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.lifedrain_uses"),
                WandConfig.lifedrainScepterUses,
                99,
                1,
                Integer.MAX_VALUE
        ));
                
        return entries;
    }
}