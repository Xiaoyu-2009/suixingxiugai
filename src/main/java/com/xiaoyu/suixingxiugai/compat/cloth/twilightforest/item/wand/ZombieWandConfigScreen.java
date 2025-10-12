package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item.wand;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.ZombieWandConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class ZombieWandConfigScreen {
    
    public static void createZombieWandConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory zombieWandCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.wand.zombie"));
        
        List<AbstractConfigListEntry> entries = createZombieWandConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            zombieWandCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createZombieWandConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.zombie.zombie_uses"),
                ZombieWandConfig.zombieWandUses,
                9,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.wand.zombie.zombie_cooldown"),
                ZombieWandConfig.zombieWandCooldown,
                0,
                0,
                Integer.MAX_VALUE
        ));

        return entries;
    }
}