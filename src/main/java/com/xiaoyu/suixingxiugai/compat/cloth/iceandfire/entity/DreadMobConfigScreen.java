package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DreadMobConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DreadMobConfigScreen {
    
    public static List<AbstractConfigListEntry> createDreadMobConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadMobsAttackAllMobs"), DreadMobConfig.dreadMobsAttackAllMobs.get())
                .setDefaultValue(true)
                .setSaveConsumer(DreadMobConfig.dreadMobsAttackAllMobs::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadLichAttackMobs"), DreadMobConfig.dreadLichAttackMobs.get())
                .setDefaultValue(true)
                .setSaveConsumer(DreadMobConfig.dreadLichAttackMobs::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadThrallAttackMobs"), DreadMobConfig.dreadThrallAttackMobs.get())
                .setDefaultValue(true)
                .setSaveConsumer(DreadMobConfig.dreadThrallAttackMobs::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadBeastAttackMobs"), DreadMobConfig.dreadBeastAttackMobs.get())
                .setDefaultValue(true)
                .setSaveConsumer(DreadMobConfig.dreadBeastAttackMobs::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadGhoulAttackMobs"), DreadMobConfig.dreadGhoulAttackMobs.get())
                .setDefaultValue(true)
                .setSaveConsumer(DreadMobConfig.dreadGhoulAttackMobs::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadScuttlerAttackMobs"), DreadMobConfig.dreadScuttlerAttackMobs.get())
                .setDefaultValue(true)
                .setSaveConsumer(DreadMobConfig.dreadScuttlerAttackMobs::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadKnightAttackMobs"), DreadMobConfig.dreadKnightAttackMobs.get())
                .setDefaultValue(true)
                .setSaveConsumer(DreadMobConfig.dreadKnightAttackMobs::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadKnightHorseAttackMobs"), DreadMobConfig.dreadKnightHorseAttackMobs.get())
                .setDefaultValue(true)
                .setSaveConsumer(DreadMobConfig.dreadKnightHorseAttackMobs::set)
                .build());

        return entries;
    }
}