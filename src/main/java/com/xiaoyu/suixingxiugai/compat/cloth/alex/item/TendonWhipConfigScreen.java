package com.xiaoyu.suixingxiugai.compat.cloth.alex.item;

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
        
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.alex.item.tendon_whip.attack_range"), TendonWhipConfig.whipAttackRange.get())
                .setDefaultValue(5.0)
                .setMin(1.0)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(TendonWhipConfig.whipAttackRange::set)
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.alex.item.tendon_whip.max_targets"), TendonWhipConfig.whipMaxTargets.get())
                .setDefaultValue(3)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(TendonWhipConfig.whipMaxTargets::set)
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.alex.item.tendon_whip.max_damage"), TendonWhipConfig.whipMaxDamage.get())
                .setDefaultValue(5.0)
                .setMin(0.0)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(TendonWhipConfig.whipMaxDamage::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.alex.item.tendon_whip.require_full_charge"), TendonWhipConfig.whipRequireFullCharge.get())
                .setDefaultValue(false)
                .setSaveConsumer(TendonWhipConfig.whipRequireFullCharge::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.alex.item.tendon_whip.quick_retract"), TendonWhipConfig.whipQuickRetract.get())
                .setDefaultValue(true)
                .setSaveConsumer(TendonWhipConfig.whipQuickRetract::set)
                .build());

        return entries;
    }
}