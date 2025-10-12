package com.xiaoyu.suixingxiugai.compat.cloth.alex.item;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.alex.item.BloodSprayerConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class BloodSprayerConfigScreen {
    
    public static List<AbstractConfigListEntry> createBloodSprayerConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.blood_sprayer.damage"),
                BloodSprayerConfig.bloodSprayerDamage,
                4.0,
                0.0,
                Double.MAX_VALUE
        ));

        return entries;
    }
}