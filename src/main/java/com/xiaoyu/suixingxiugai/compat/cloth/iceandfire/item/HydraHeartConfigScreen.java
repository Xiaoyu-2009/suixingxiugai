package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
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
        
        entries.add(ConfigEntryHelper.createDoubleList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.potion_thresholds"),
                new ArrayList<>(HydraHeartConfig.hydraHeartPotionThresholds.get()),
                new ArrayList<>(List.of(0.75, 0.5, 0.25)),
                list -> HydraHeartConfig.hydraHeartPotionThresholds.set(new ArrayList<>(list))
        ));
                
        entries.add(ConfigEntryHelper.createIntList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.potion_levels"),
                new ArrayList<>(HydraHeartConfig.hydraHeartPotionLevels.get()),
                new ArrayList<>(List.of(0, 1, 2, 3)),
                list -> HydraHeartConfig.hydraHeartPotionLevels.set(new ArrayList<>(list))
        ));
                
        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.potion_effect"),
                HydraHeartConfig.hydraHeartPotionEffect,
                "minecraft:regeneration"
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.potion_duration"),
                HydraHeartConfig.hydraHeartPotionDuration,
                900,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.apply_effect_if_already_has"),
                HydraHeartConfig.hydraHeartApplyEffectIfAlreadyHas,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.hydra_heart.stack_potion_levels"),
                HydraHeartConfig.hydraHeartStackPotionLevels,
                true
        ));

        return entries;
    }
}