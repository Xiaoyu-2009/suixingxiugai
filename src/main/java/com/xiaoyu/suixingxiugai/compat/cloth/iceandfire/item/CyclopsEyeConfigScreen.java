package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
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
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePotionEffectRadius"),
                CyclopsEyeConfig.cyclopsEyePotionEffectRadius,
                10.0,
                1.0,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePotionEffectLevel"),
                CyclopsEyeConfig.cyclopsEyePotionEffectLevel,
                1,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePotionEffectId"),
                CyclopsEyeConfig.cyclopsEyePotionEffectId,
                "minecraft:weakness"
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePotionEffectDuration"),
                CyclopsEyeConfig.cyclopsEyePotionEffectDuration,
                20,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyeMinAffectedMobs"),
                CyclopsEyeConfig.cyclopsEyeMinAffectedMobs,
                1,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyeDurabilityReduction"),
                CyclopsEyeConfig.cyclopsEyeDurabilityReduction,
                1,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyeDurabilityReductionSpeed"),
                CyclopsEyeConfig.cyclopsEyeDurabilityReductionSpeed,
                120,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyePlayBreakSound"),
                CyclopsEyeConfig.cyclopsEyePlayBreakSound,
                true
        ));
                
        entries.add(ConfigEntryHelper.createStringDropdownMenu(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.cyclops_eye.cyclopsEyeTargetEntityType"),
                CyclopsEyeConfig.cyclopsEyeTargetEntityType,
                "living",
                List.of("mob", "living", "player", "all", "animal", "monster", "ambient", "water_animal", "flying")
        ));

        return entries;
    }
}