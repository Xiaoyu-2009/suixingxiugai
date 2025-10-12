package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.GorgonHeadConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class GorgonHeadConfigScreen {
    
    public static List<AbstractConfigListEntry> createGorgonHeadConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.gorgon_head.gorgonHeadUses"),
                GorgonHeadConfig.gorgonHeadUses,
                1,
                1,
                Integer.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.gorgon_head.gorgonHeadPlayBreakSound"),
                GorgonHeadConfig.gorgonHeadPlayBreakSound,
                true
        ));
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.gorgon_head.gorgonHeadMustPetrifyToConsume"),
                GorgonHeadConfig.gorgonHeadMustPetrifyToConsume,
                true
        ));
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.gorgon_head.gorgonHeadCanPetrifyAllEntities"),
                GorgonHeadConfig.gorgonHeadCanPetrifyAllEntities,
                false
        ));

        return entries;
    }
}