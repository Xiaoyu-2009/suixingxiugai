package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item;

import com.xiaoyu.suixingxiugai.config.iceandfire.item.CyclopsEyeConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CyclopsEyeConfigScreen {
    
    public static List<AbstractConfigListEntry> createCyclopsEyeConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePotionEffectRadius"), CyclopsEyeConfig.cyclopsEyePotionEffectRadius.get())
                .setDefaultValue(10.0)
                .setMin(1.0)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyePotionEffectRadius::set)
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePotionEffectLevel"), CyclopsEyeConfig.cyclopsEyePotionEffectLevel.get())
                .setDefaultValue(1)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyePotionEffectLevel::set)
                .build());
                
        entries.add(entryBuilder.startStrField(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePotionEffectId"), CyclopsEyeConfig.cyclopsEyePotionEffectId.get())
                .setDefaultValue("minecraft:weakness")
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyePotionEffectId::set)
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePotionEffectDuration"), CyclopsEyeConfig.cyclopsEyePotionEffectDuration.get())
                .setDefaultValue(20)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyePotionEffectDuration::set)
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyeMinAffectedMobs"), CyclopsEyeConfig.cyclopsEyeMinAffectedMobs.get())
                .setDefaultValue(1)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyeMinAffectedMobs::set)
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyeDurabilityReduction"), CyclopsEyeConfig.cyclopsEyeDurabilityReduction.get())
                .setDefaultValue(1)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyeDurabilityReduction::set)
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyeDurabilityReductionSpeed"), CyclopsEyeConfig.cyclopsEyeDurabilityReductionSpeed.get())
                .setDefaultValue(120)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyeDurabilityReductionSpeed::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePlayBreakSound"), CyclopsEyeConfig.cyclopsEyePlayBreakSound.get())
                .setDefaultValue(true)
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyePlayBreakSound::set)
                .build());
                
        entries.add(entryBuilder.startStringDropdownMenu(Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyeTargetEntityType"), CyclopsEyeConfig.cyclopsEyeTargetEntityType.get())
                .setDefaultValue("living")
                .setSelections(List.of("mob", "living", "player", "all", "animal", "monster", "ambient", "water_animal", "flying"))
                .setSaveConsumer(CyclopsEyeConfig.cyclopsEyeTargetEntityType::set)
                .build());

        return entries;
    }
}