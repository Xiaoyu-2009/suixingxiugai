package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.AlphaYetiConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class AlphaYetiConfigScreen {
    
    public static void createAlphaYetiConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory alphaYetiCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti"));
        
        List<AbstractConfigListEntry> entries = createAlphaYetiConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            alphaYetiCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createAlphaYetiConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.max_health"),
                AlphaYetiConfig.alphaYetiMaxHealth,
                200.0D,
                1.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.movement_speed"),
                AlphaYetiConfig.alphaYetiMovementSpeed,
                0.38D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.attack_damage"),
                AlphaYetiConfig.alphaYetiAttackDamage,
                1.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.follow_range"),
                AlphaYetiConfig.alphaYetiFollowRange,
                40.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.knockback_resistance"),
                AlphaYetiConfig.alphaYetiKnockbackResistance,
                0.5D,
                0.0D,
                1.0D
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.xp_reward"),
                AlphaYetiConfig.alphaYetiXpReward,
                317,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.can_throw_entity"),
                AlphaYetiConfig.alphaYetiCanThrowEntity,
                true
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.can_use_ice_bomb"),
                AlphaYetiConfig.alphaYetiCanUseIceBomb,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.can_use_ice_rampage"),
                AlphaYetiConfig.alphaYetiCanUseIceRampage,
                true
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.ice_rampage_timeout"),
                AlphaYetiConfig.alphaYetiIceRampageTimeout,
                10,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.ice_rampage_duration"),
                AlphaYetiConfig.alphaYetiIceRampageDuration,
                180,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.ice_rampage_tired_duration"),
                AlphaYetiConfig.alphaYetiIceRampageTiredDuration,
                100,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti.can_use_falling_ice"),
                AlphaYetiConfig.alphaYetiCanUseFallingIce,
                true
        ));

        return entries;
    }
}