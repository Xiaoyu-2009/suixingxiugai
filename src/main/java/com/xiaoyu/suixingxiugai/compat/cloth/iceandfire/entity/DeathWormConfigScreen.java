package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DeathWormConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DeathWormConfigScreen {
    
    public static List<AbstractConfigListEntry> createDeathWormConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();

        entries.add(ConfigEntryHelper.createStringList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.deathworm.passable_blocks"),
                DeathWormConfig.deathWormPassableBlocks,
                new ArrayList<>(List.of("minecraft:sand", "minecraft:red_sand"))
        ));

        return entries;
    }
}