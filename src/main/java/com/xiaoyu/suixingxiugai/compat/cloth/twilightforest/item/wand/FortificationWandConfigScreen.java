package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item.wand;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.FortificationWandConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class FortificationWandConfigScreen {
    
    public static void createFortificationWandConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory fortificationWandCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.wand.fortification"));
        
        List<AbstractConfigListEntry> entries = createFortificationWandConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            fortificationWandCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createFortificationWandConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.fortification.enable_fortification_targeting"),
                FortificationWandConfig.enableFortificationWandTargeting,
                false
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.fortification.fortification_shield_amount"),
                FortificationWandConfig.fortificationWandShieldAmount,
                5,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.fortification.enable_fortification_invulnerability"),
                FortificationWandConfig.enableFortificationWandInvulnerability,
                false
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.fortification.fortification_uses"),
                FortificationWandConfig.fortificationWandUses,
                9,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.fortification.fortification_cooldown"),
                FortificationWandConfig.fortificationWandCooldown,
                1200,
                0,
                Integer.MAX_VALUE
        ));

        return entries;
    }
}