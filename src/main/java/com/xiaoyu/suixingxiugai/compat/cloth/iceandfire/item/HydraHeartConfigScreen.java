package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item;

import com.xiaoyu.suixingxiugai.config.iceandfire.item.HydraHeartConfig;

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
        
        List<Double> potionThresholds = new ArrayList<>(HydraHeartConfig.hydraHeartPotionThresholds.get());
        entries.add(entryBuilder.startDoubleList(Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.potion_thresholds"), potionThresholds)
                .setDefaultValue(new ArrayList<>(List.of(0.75, 0.5, 0.25)))
                .setSaveConsumer(list -> HydraHeartConfig.hydraHeartPotionThresholds.set(new ArrayList<>(list)))
                .build());
                
        List<Integer> potionLevels = new ArrayList<>(HydraHeartConfig.hydraHeartPotionLevels.get());
        entries.add(entryBuilder.startIntList(Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.potion_levels"), potionLevels)
                .setDefaultValue(new ArrayList<>(List.of(0, 1, 2, 3)))
                .setSaveConsumer(list -> HydraHeartConfig.hydraHeartPotionLevels.set(new ArrayList<>(list)))
                .build());
                
        entries.add(entryBuilder.startStrField(Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.potion_effect"), HydraHeartConfig.hydraHeartPotionEffect.get())
                .setDefaultValue("minecraft:regeneration")
                .setSaveConsumer(HydraHeartConfig.hydraHeartPotionEffect::set)
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.potion_duration"), HydraHeartConfig.hydraHeartPotionDuration.get())
                .setDefaultValue(900)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(HydraHeartConfig.hydraHeartPotionDuration::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.apply_effect_if_already_has"), HydraHeartConfig.hydraHeartApplyEffectIfAlreadyHas.get())
                .setDefaultValue(true)
                .setSaveConsumer(HydraHeartConfig.hydraHeartApplyEffectIfAlreadyHas::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.stack_potion_levels"), HydraHeartConfig.hydraHeartStackPotionLevels.get())
                .setDefaultValue(true)
                .setSaveConsumer(HydraHeartConfig.hydraHeartStackPotionLevels::set)
                .build());

        return entries;
    }
}