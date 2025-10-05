package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class LichConfigScreen {
    
    public static void createLichConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory lichCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.lich"));
        
        List<AbstractConfigListEntry> entries = createLichConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            lichCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createLichConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.max_health"),
                LichConfig.lichMaxHealth,
                100.0D,
                1.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.movement_speed"),
                LichConfig.lichMovementSpeed,
                0.45D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.attack_damage"),
                LichConfig.lichAttackDamage,
                3.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.xp_reward"),
                LichConfig.lichXpReward,
                217,
                0,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.enable_shield_modification"),
                LichConfig.enableLichShieldModification,
                false
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.shield_strength"),
                LichConfig.lichShieldStrength,
                6,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phantom_summon_max_count"),
                LichConfig.lichPhantomSummonMaxCount,
                4,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phantom_can_be_attacked"),
                LichConfig.lichPhantomCanBeAttacked,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phantom_drop_chest"),
                LichConfig.lichPhantomDropChest,
                false
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.can_spawn_phantom"),
                LichConfig.lichCanSpawnPhantom,
                true
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.can_absorb_minions"),
                LichConfig.lichCanAbsorbMinions,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.can_absorb_mobs_phase2"),
                LichConfig.lichCanAbsorbMobsInPhase2,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.can_absorb_minions_phase2"),
                LichConfig.lichCanAbsorbMinionsInPhase2,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.can_absorb_mobs_phase3"),
                LichConfig.lichCanAbsorbMobsInPhase3,
                true
        ));

        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_mob_absorb_heal_amount"),
                LichConfig.lichPhase2MobAbsorbHealAmount,
                2.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_mob_absorb_health_threshold"),
                LichConfig.lichPhase2MobAbsorbHealthThreshold,
                50.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_minion_absorb_heal_amount"),
                LichConfig.lichPhase2MinionAbsorbHealAmount,
                10.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_minion_absorb_health_threshold"),
                LichConfig.lichPhase2MinionAbsorbHealthThreshold,
                50.0D,
                0.0D,
                Double.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phase3_mob_absorb_heal_amount"),
                LichConfig.lichPhase3MobAbsorbHealAmount,
                20.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phase3_mob_absorb_health_threshold"),
                LichConfig.lichPhase3MobAbsorbHealthThreshold,
                80.0D,
                0.0D,
                Double.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phase1_can_teleport"),
                LichConfig.lichPhase1CanTeleport,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_can_teleport"),
                LichConfig.lichPhase2CanTeleport,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.lich.phase3_can_teleport"),
                LichConfig.lichPhase3CanTeleport,
                true
        ));
                
        return entries;
    }
}