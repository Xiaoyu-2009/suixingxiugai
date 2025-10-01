package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.HydraConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class HydraConfigScreen {
    
    public static List<AbstractConfigListEntry> createHydraConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.iceandfire.entity.hydra.singleHitDamageToKill"), HydraConfig.singleHitDamageToKill.get())
                .setDefaultValue(0.0)
                .setMin(0.0)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(HydraConfig.singleHitDamageToKill::set)
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.iceandfire.entity.hydra.cumulativeDamageToKill"), HydraConfig.cumulativeDamageToKill.get())
                .setDefaultValue(0.0)
                .setMin(0.0)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(HydraConfig.cumulativeDamageToKill::set)
                .build());
                
        List<String> damageTypesToKill = new ArrayList<>(HydraConfig.damageTypesToKill.get());
        entries.add(entryBuilder.startStrList(Component.translatable("config.suixingxiugai.iceandfire.entity.hydra.damageTypesToKill"), damageTypesToKill)
                .setDefaultValue(new ArrayList<>(List.of("")))
                .setSaveConsumer(list -> HydraConfig.damageTypesToKill.set(new ArrayList<>(list)))
                .build());

        return entries;
    }
}