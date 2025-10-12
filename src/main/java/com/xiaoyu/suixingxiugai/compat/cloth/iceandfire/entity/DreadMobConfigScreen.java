package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.entity;

import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DreadMobConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

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
        
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadMobsAttackAllMobs"),
                DreadMobConfig.dreadMobsAttackAllMobs,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadLichAttackMobs"),
                DreadMobConfig.dreadLichAttackMobs,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadThrallAttackMobs"),
                DreadMobConfig.dreadThrallAttackMobs,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadBeastAttackMobs"),
                DreadMobConfig.dreadBeastAttackMobs,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadGhoulAttackMobs"),
                DreadMobConfig.dreadGhoulAttackMobs,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadScuttlerAttackMobs"),
                DreadMobConfig.dreadScuttlerAttackMobs,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadKnightAttackMobs"),
                DreadMobConfig.dreadKnightAttackMobs,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.entity.dread_mob.dreadKnightHorseAttackMobs"),
                DreadMobConfig.dreadKnightHorseAttackMobs,
                true
        ));

        return entries;
    }
}