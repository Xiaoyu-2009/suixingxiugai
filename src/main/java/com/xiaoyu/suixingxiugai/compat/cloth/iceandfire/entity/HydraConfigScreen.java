package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.HydraConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

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

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.hydra.enableSingleHitKill"),
                HydraConfig.enableSingleHitKill,
                false
        ));
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.hydra.singleHitDamageToKill"),
                HydraConfig.singleHitDamageToKill,
                2009.0,
                0.0,
                Double.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.hydra.enableCumulativeDamageKill"),
                HydraConfig.enableCumulativeDamageKill,
                false
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.hydra.cumulativeDamageToKill"),
                HydraConfig.cumulativeDamageToKill,
                2009.0,
                0.0,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createStringList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.hydra.damageTypesToKill"),
                HydraConfig.damageTypesToKill,
                new ArrayList<>(List.of(""))
        ));

        return entries;
    }
}