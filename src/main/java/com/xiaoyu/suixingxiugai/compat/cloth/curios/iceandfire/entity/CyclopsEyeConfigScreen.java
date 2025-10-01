package com.xiaoyu.suixingxiugai.compat.cloth.curios.iceandfire.entity;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyu.suixingxiugai.config.curios.iceandfire.CyclopsEyeConfig;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CyclopsEyeConfigScreen {
    
    public static List<AbstractConfigListEntry> createCyclopsEyeConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.curios.iceandfire.cyclopsEyeWorkAsCurio"), CyclopsEyeConfig.cyclopsEyeWorkAsCurio.get())
                .setDefaultValue(false)
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyeWorkAsCurio::set)
                .build());

        return entries;
    }
}