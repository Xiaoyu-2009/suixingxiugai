package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire;

import com.xiaoyu.suixingxiugai.config.iceandfire.DragonDenConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder; 
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DragonDenConfigScreen {
    
    public static List<AbstractConfigListEntry> createDragonDenConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.dragonden.undergroundDragonDenMinYLevel"),
                DragonDenConfig.undergroundDragonDenMinYLevel,
                10,
                -200,
                256
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.dragonden.undergroundDragonDenMaxYLevel"),
                DragonDenConfig.undergroundDragonDenMaxYLevel,
                50,
                -200,
                256
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.dragonden.undergroundDragonDenCanGenerateInMidAir"),
                DragonDenConfig.undergroundDragonDenCanGenerateInMidAir,
                false
        ));

        return entries;
    }
}