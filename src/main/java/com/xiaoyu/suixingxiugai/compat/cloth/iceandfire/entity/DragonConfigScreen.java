package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder; 
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DragonConfigScreen {
    
    public static List<AbstractConfigListEntry> createDragonConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.enableDragonResurrectionCommand"), DragonConfig.enableDragonResurrectionCommand.get())
                .setDefaultValue(true)
                .setSaveConsumer(DragonConfig.enableDragonResurrectionCommand::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.wildDragonDefaultSleeping"), DragonConfig.wildDragonDefaultSleeping.get())
                .setDefaultValue(true)
                .setSaveConsumer(DragonConfig.wildDragonDefaultSleeping::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.wildDragonDefaultSleepingSurface"), DragonConfig.wildDragonDefaultSleepingSurface.get())
                .setDefaultValue(true)
                .setSaveConsumer(DragonConfig.wildDragonDefaultSleepingSurface::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dragon.wildDragonDefaultSleepingUnderground"), DragonConfig.wildDragonDefaultSleepingUnderground.get())
                .setDefaultValue(true)
                .setSaveConsumer(DragonConfig.wildDragonDefaultSleepingUnderground::set)
                .build());

        return entries;
    }
}