package com.xiaoyu.suixingxiugai.compat.cloth.mutantmonsters.entity;

import com.xiaoyu.suixingxiugai.config.mutantmonsters.entity.MutantZombieConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class MutantZombieConfigScreen {
    
    public static List<AbstractConfigListEntry> createMutantZombieConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createIntField(
            entryBuilder,
            Component.translatable("config.suixingxiugai.mutantmonsters.mutant_zombie.resurrection_count"),
            MutantZombieConfig.MUTANT_ZOMBIE_RESURRECTION_COUNT,
            4,
            1,
            Integer.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
            entryBuilder,
            Component.translatable("config.suixingxiugai.mutantmonsters.mutant_zombie.enable_slam_ground_attack"),
            MutantZombieConfig.MUTANT_ZOMBIE_ENABLE_SLAM_GROUND_ATTACK,
            true
        ));
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
            entryBuilder,
            Component.translatable("config.suixingxiugai.mutantmonsters.mutant_zombie.enable_throw_attack"),
            MutantZombieConfig.MUTANT_ZOMBIE_ENABLE_THROW_ATTACK,
            true
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
            entryBuilder,
            Component.translatable("config.suixingxiugai.mutantmonsters.mutant_zombie.enable_roar_attack"),
            MutantZombieConfig.MUTANT_ZOMBIE_ENABLE_ROAR_ATTACK,
            true
        ));

        return entries;
    }
}