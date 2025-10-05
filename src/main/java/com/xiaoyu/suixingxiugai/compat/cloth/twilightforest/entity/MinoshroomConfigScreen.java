package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.MinoshroomConfig;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class MinoshroomConfigScreen {
    
    public static void createMinoshroomConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory minoshroomCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.minoshroom"));
        
        List<AbstractConfigListEntry> entries = createMinoshroomConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            minoshroomCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createMinoshroomConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.max_health"),
                MinoshroomConfig.minoshroomMaxHealth,
                120.0D,
                1.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.knockback_resistance"),
                MinoshroomConfig.minoshroomKnockbackResistance,
                0.5D,
                0.0D,
                1.0D
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.movement_speed"),
                MinoshroomConfig.minoshroomMovementSpeed,
                0.25D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.attack_damage"),
                MinoshroomConfig.minoshroomAttackDamage,
                4.0D,
                0.0D,
                Double.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.xp_reward"),
                MinoshroomConfig.minoshroomXpReward,
                100,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.difficulty_attack_damage_boost_normal"),
                MinoshroomConfig.minoshroomDifficultyAttackDamageBoostNormal,
                3.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.difficulty_attack_damage_boost_hard"),
                MinoshroomConfig.minoshroomDifficultyAttackDamageBoostHard,
                6.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.ground_attack_charge_time"),
                MinoshroomConfig.minoshroomGroundAttackChargeTime,
                30,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.ground_attack_damage_multiplier"),
                MinoshroomConfig.minoshroomGroundAttackDamageMultiplier,
                0.5D,
                0.0D,
                Double.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.can_ground_attack"),
                MinoshroomConfig.minoshroomCanGroundAttack,
                true
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.can_charge"),
                MinoshroomConfig.minoshroomCanCharge,
                true
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.running_charge_time"),
                MinoshroomConfig.minoshroomChargeTime,
                15,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.running_charge_can_break_blocks"),
                MinoshroomConfig.minoshroomChargeCanBreakBlocks,
                true
        ));

        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.minoshroom.running_charge_knockback_height"),
                MinoshroomConfig.minoshroomChargeKnockbackHeight,
                0.35D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        return entries;
    }
}