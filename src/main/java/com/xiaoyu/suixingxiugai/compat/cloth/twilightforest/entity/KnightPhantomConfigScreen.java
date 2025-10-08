package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.KnightPhantomConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class KnightPhantomConfigScreen {
    
    public static void createKnightPhantomConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory knightPhantomCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.knight_phantom"));
        
        List<AbstractConfigListEntry> entries = createKnightPhantomConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            knightPhantomCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createKnightPhantomConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.max_health"),
                KnightPhantomConfig.knightPhantomMaxHealth,
                35.0D,
                1.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.flight_speed"),
                KnightPhantomConfig.knightPhantomFlightSpeed,
                0.1D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.attack_damage"),
                KnightPhantomConfig.knightPhantomAttackDamage,
                1.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.xp_reward"),
                KnightPhantomConfig.knightPhantomXpReward,
                93,
                0,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.armor"),
                KnightPhantomConfig.knightPhantomArmor,
                4.0D,
                0.0D,
                Double.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.can_no_clip"),
                KnightPhantomConfig.knightPhantomCanNoClip,
                true
        ));

        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.helmet_item"),
                KnightPhantomConfig.knightPhantomHelmetItem,
                "twilightforest:phantom_helmet"
        ));
        
        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.chestplate_item"),
                KnightPhantomConfig.knightPhantomChestplateItem,
                "twilightforest:phantom_chestplate"
        ));
        
        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.leggings_item"),
                KnightPhantomConfig.knightPhantomLeggingsItem,
                "minecraft:air"
        ));
        
        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.boots_item"),
                KnightPhantomConfig.knightPhantomBootsItem,
                "minecraft:air"
        ));

        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.main_hand_number0_item"),
                KnightPhantomConfig.knightPhantomMainHandNumber0Item,
                "twilightforest:knightmetal_sword"
        ));
        
        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.main_hand_number1_item"),
                KnightPhantomConfig.knightPhantomMainHandNumber1Item,
                "twilightforest:knightmetal_axe"
        ));
        
        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.main_hand_number2_item"),
                KnightPhantomConfig.knightPhantomMainHandNumber2Item,
                "twilightforest:knightmetal_pickaxe"
        ));
        
        entries.add(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.knight_phantom.off_hand_item"),
                KnightPhantomConfig.knightPhantomOffHandItem,
                "minecraft:air"
        ));
                
        return entries;
    }
}