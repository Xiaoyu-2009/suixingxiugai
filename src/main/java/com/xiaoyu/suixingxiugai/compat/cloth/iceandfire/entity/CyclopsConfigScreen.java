package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.CyclopsConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder; 
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CyclopsConfigScreen {
    
    public static List<AbstractConfigListEntry> createCyclopsConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.cyclops.cyclopsBiteStrength"),
                CyclopsConfig.cyclopsBiteStrength,
                40.0,
                0.0,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.cyclops.cyclopsCanStompAttack"),
                CyclopsConfig.cyclopsCanStompAttack,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.cyclops.cyclopsCanGrabAttack"),
                CyclopsConfig.cyclopsCanGrabAttack,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.cyclops.cyclopsCanKickAttack"),
                CyclopsConfig.cyclopsCanKickAttack,
                true
        ));

        return entries;
    }
}