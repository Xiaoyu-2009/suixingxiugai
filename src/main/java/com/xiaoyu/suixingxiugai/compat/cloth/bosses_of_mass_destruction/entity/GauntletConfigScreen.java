package com.xiaoyu.suixingxiugai.compat.cloth.bosses_of_mass_destruction.entity;

import com.xiaoyu.suixingxiugai.config.bosses_of_mass_destruction.entity.GauntletConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class GauntletConfigScreen {
    
    public static List<AbstractConfigListEntry> createGauntletConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.bosses_of_mass_destruction.entity.gauntlet.defense_can_be_attacked"), GauntletConfig.gauntletDefenseCanBeAttacked.get())
                .setDefaultValue(false)
                .setSaveConsumer(GauntletConfig.gauntletDefenseCanBeAttacked::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.bosses_of_mass_destruction.entity.gauntlet.idle_heal_enabled"), GauntletConfig.gauntletIdleHealEnabled.get())
                .setDefaultValue(true)
                .setSaveConsumer(GauntletConfig.gauntletIdleHealEnabled::set)
                .build());

        return entries;
    }
}