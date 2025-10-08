package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.CyclopsConfig;

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
        
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.iceandfire.entity.cyclops.cyclopsBiteStrength"), CyclopsConfig.cyclopsBiteStrength.get())
                .setDefaultValue(40.0)
                .setMin(0.0)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(CyclopsConfig.cyclopsBiteStrength::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.cyclops.cyclopsCanStompAttack"), CyclopsConfig.cyclopsCanStompAttack.get())
                .setDefaultValue(true)
                .setSaveConsumer(CyclopsConfig.cyclopsCanStompAttack::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.cyclops.cyclopsCanGrabAttack"), CyclopsConfig.cyclopsCanGrabAttack.get())
                .setDefaultValue(true)
                .setSaveConsumer(CyclopsConfig.cyclopsCanGrabAttack::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.cyclops.cyclopsCanKickAttack"), CyclopsConfig.cyclopsCanKickAttack.get())
                .setDefaultValue(true)
                .setSaveConsumer(CyclopsConfig.cyclopsCanKickAttack::set)
                .build());

        return entries;
    }
}