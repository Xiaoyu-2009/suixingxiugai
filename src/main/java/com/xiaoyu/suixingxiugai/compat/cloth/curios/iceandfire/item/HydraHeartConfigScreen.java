package com.xiaoyu.suixingxiugai.compat.cloth.curios.iceandfire.item;

import com.xiaoyu.suixingxiugai.config.curios.iceandfire.HydraHeartConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class HydraHeartConfigScreen {
    
    public static List<AbstractConfigListEntry> createHydraHeartConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.curios.iceandfire.hydraHeartWorkAsCurio"), HydraHeartConfig.hydraHeartWorkAsCurio.get())
                .setDefaultValue(false)
                .setSaveConsumer(HydraHeartConfig.hydraHeartWorkAsCurio::set)
                .build());

        return entries;
    }
}