package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item;

import com.xiaoyu.suixingxiugai.config.iceandfire.item.DragonFluteConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder; 

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DragonFluteConfigScreen {
    
    public static List<AbstractConfigListEntry> createDragonFluteConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.dragon_flute.dragonFluteAffectsWildDragons"),
                DragonFluteConfig.dragonFluteAffectsWildDragons,
                false
        ));
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.dragon_flute.dragonFluteCooldown"),
                DragonFluteConfig.dragonFluteCooldown,
                60,
                0,
                Integer.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.dragon_flute.dragonFluteDistance"),
                DragonFluteConfig.dragonFluteDistance,
                8,
                1,
                Integer.MAX_VALUE
        ));

        return entries;
    }
}