package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.NagaConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class NagaConfigScreen {
    
    public static void createNagaConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory nagaCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.naga"));
        
        List<AbstractConfigListEntry> entries = createNagaConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            nagaCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createNagaConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.max_health"),
                NagaConfig.nagaMaxHealth,
                120.0D,
                1.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.movement_speed"),
                NagaConfig.nagaMovementSpeed,
                0.5D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.head_attack_damage"),
                NagaConfig.nagaAttackDamage,
                5.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.segment_attack_damage"),
                NagaConfig.nagaSegmentAttackDamage,
                2.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.segment_attack_damage_multiplier_animals"),
                NagaConfig.nagaSegmentAttackDamageMultiplierAgainstAnimals,
                3.0D,
                1.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.follow_range"),
                NagaConfig.nagaFollowRange,
                80.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.knockback_resistance"),
                NagaConfig.nagaKnockbackResistance,
                0.25D,
                0.0D,
                1.0D
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.xp_reward"),
                NagaConfig.nagaXpReward,
                217,
                0,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.difficulty_health_boost_normal"),
                NagaConfig.nagaDifficultyHealthBoostNormal,
                80,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.difficulty_health_boost_hard"),
                NagaConfig.nagaDifficultyHealthBoostHard,
                130,
                0,
                Integer.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.difficulty_head_attack_damage_boost_normal"),
                NagaConfig.nagaDifficultyAttackDamageBoostNormal,
                2.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.difficulty_head_attack_damage_boost_hard"),
                NagaConfig.nagaDifficultyAttackDamageBoostHard,
                3.0D,
                0.0D,
                Double.MAX_VALUE
        ));
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.difficulty_segment_attack_damage_boost_normal"),
                NagaConfig.nagaDifficultySegmentAttackDamageBoostNormal,
                1.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.difficulty_segment_attack_damage_boost_hard"),
                NagaConfig.nagaDifficultySegmentAttackDamageBoostHard,
                1.5D,
                0.0D,
                Double.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.stun_duration"),
                NagaConfig.nagaStunDuration,
                60,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.stun_damage_threshold"),
                NagaConfig.nagaStunDamageThreshold,
                15,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.healing_delay"),
                NagaConfig.nagaHealingDelay,
                600,
                0,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.courtyard_bound_x"),
                NagaConfig.nagaCourtyardBoundX,
                46,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.courtyard_bound_z"),
                NagaConfig.nagaCourtyardBoundZ,
                46,
                1,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.courtyard_bound_y"),
                NagaConfig.nagaCourtyardBoundY,
                7,
                1,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.shield_damage_on_charge_normal"),
                NagaConfig.nagaShieldDamageOnCharge,
                5,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.shield_damage_on_charge_enraged"),
                NagaConfig.nagaShieldDamageOnChargeEnraged,
                10,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.shield_cooldown"),
                NagaConfig.nagaShieldCooldown,
                200,
                0,
                Integer.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.head_charge_push_force"),
                NagaConfig.nagaHeadChargePushForce,
                2.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.head_charge_recoil_force"),
                NagaConfig.nagaHeadChargeRecoilForce,
                0.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.segment_charge_push_force"),
                NagaConfig.nagaSegmentChargePushForce,
                2.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.segment_charge_recoil_force"),
                NagaConfig.nagaSegmentChargeRecoilForce,
                0.0D,
                0.0D,
                Double.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.attack_knockback_force_normal"),
                NagaConfig.nagaAttackKnockbackForce,
                2.0D,
                0.0D,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.attack_knockback_force_enraged"),
                NagaConfig.nagaAttackKnockbackForceEnraged,
                4.0D,
                0.0D,
                Double.MAX_VALUE
        ));

        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.segment_damage_transfer_ratio"),
                NagaConfig.nagaSegmentDamageTransferRatio,
                2.0D/3.0D,
                0.0D,
                1.0D
        ));

        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.can_destroy_blocks"),
                NagaConfig.nagaCanDestroyBlocks,
                true
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.can_destroy_blocks_on_charge"),
                NagaConfig.nagaCanDestroyBlocksOnCharge,
                true
        ));

        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.death_animation_start_delay"),
                NagaConfig.nagaDeathAnimationStartDelay,
                24,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.twilightforest.naga.death_animation_duration"),
                NagaConfig.nagaDeathAnimationDuration,
                120,
                1,
                Integer.MAX_VALUE
        ));
                
        return entries;
    }
}