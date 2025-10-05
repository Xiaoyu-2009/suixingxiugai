package com.xiaoyu.suixingxiugai.compat.cloth.alex.item;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.alex.item.TendonWhipConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TendonWhipConfigScreen {
    
    public static List<AbstractConfigListEntry> createTendonWhipConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.tendon_whip.attack_range"),
                TendonWhipConfig.whipAttackRange,
                5.0,
                1.0,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.tendon_whip.max_targets"),
                TendonWhipConfig.whipMaxTargets,
                3,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.tendon_whip.max_damage"),
                TendonWhipConfig.whipMaxDamage,
                5.0,
                0.0,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.tendon_whip.require_full_charge"),
                TendonWhipConfig.whipRequireFullCharge,
                false
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.alex.item.tendon_whip.quick_retract"),
                TendonWhipConfig.whipQuickRetract,
                true
        ));

        return entries;
    }
}