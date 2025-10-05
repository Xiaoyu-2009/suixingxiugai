package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DeathWormConfig;

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

        List<String> passableBlocks = new ArrayList<>(DeathWormConfig.deathWormPassableBlocks.get());
        entries.add(entryBuilder.startStrList(Component.translatable("config.suixingxiugai.iceandfire.entity.deathworm.passable_blocks"), passableBlocks)
                .setDefaultValue(new ArrayList<>(List.of("minecraft:sand", "minecraft:red_sand")))
                .setSaveConsumer(list -> DeathWormConfig.deathWormPassableBlocks.set(new ArrayList<>(list)))
                .build());

        return entries;
    }
}