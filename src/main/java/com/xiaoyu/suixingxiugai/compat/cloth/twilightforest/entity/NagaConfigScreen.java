package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity;

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
        
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.max_health"), NagaConfig.nagaMaxHealth.get())
                .setDefaultValue(120.0D)
                .setMin(1.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaMaxHealth.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.movement_speed"), NagaConfig.nagaMovementSpeed.get())
                .setDefaultValue(0.5D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaMovementSpeed.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.head_attack_damage"), NagaConfig.nagaAttackDamage.get())
                .setDefaultValue(5.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaAttackDamage.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.segment_attack_damage"), NagaConfig.nagaSegmentAttackDamage.get())
                .setDefaultValue(2.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaSegmentAttackDamage.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.segment_attack_damage_multiplier_animals"), NagaConfig.nagaSegmentAttackDamageMultiplierAgainstAnimals.get())
                .setDefaultValue(3.0D)
                .setMin(1.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaSegmentAttackDamageMultiplierAgainstAnimals.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.follow_range"), NagaConfig.nagaFollowRange.get())
                .setDefaultValue(80.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaFollowRange.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.knockback_resistance"), NagaConfig.nagaKnockbackResistance.get())
                .setDefaultValue(0.25D)
                .setMin(0.0D)
                .setMax(1.0D)
                .setSaveConsumer(value -> NagaConfig.nagaKnockbackResistance.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.xp_reward"), NagaConfig.nagaXpReward.get())
                .setDefaultValue(217)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaXpReward.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.difficulty_health_boost_normal"), NagaConfig.nagaDifficultyHealthBoostNormal.get())
                .setDefaultValue(80)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaDifficultyHealthBoostNormal.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.difficulty_health_boost_hard"), NagaConfig.nagaDifficultyHealthBoostHard.get())
                .setDefaultValue(130)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaDifficultyHealthBoostHard.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.stun_duration"), NagaConfig.nagaStunDuration.get())
                .setDefaultValue(60)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaStunDuration.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.stun_damage_threshold"), NagaConfig.nagaStunDamageThreshold.get())
                .setDefaultValue(15)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaStunDamageThreshold.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.healing_delay"), NagaConfig.nagaHealingDelay.get())
                .setDefaultValue(600)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaHealingDelay.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.courtyard_bound_x"), NagaConfig.nagaCourtyardBoundX.get())
                .setDefaultValue(46)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaCourtyardBoundX.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.courtyard_bound_z"), NagaConfig.nagaCourtyardBoundZ.get())
                .setDefaultValue(46)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaCourtyardBoundZ.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.courtyard_bound_y"), NagaConfig.nagaCourtyardBoundY.get())
                .setDefaultValue(7)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaCourtyardBoundY.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.shield_damage_on_charge_normal"), NagaConfig.nagaShieldDamageOnCharge.get())
                .setDefaultValue(5)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaShieldDamageOnCharge.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.shield_damage_on_charge_enraged"), NagaConfig.nagaShieldDamageOnChargeEnraged.get())
                .setDefaultValue(10)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaShieldDamageOnChargeEnraged.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.shield_cooldown"), NagaConfig.nagaShieldCooldown.get())
                .setDefaultValue(200)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaShieldCooldown.set(value))
                .build());

        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.head_charge_push_force"), NagaConfig.nagaHeadChargePushForce.get())
                .setDefaultValue(2.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaHeadChargePushForce.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.head_charge_recoil_force"), NagaConfig.nagaHeadChargeRecoilForce.get())
                .setDefaultValue(0.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaHeadChargeRecoilForce.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.segment_charge_push_force"), NagaConfig.nagaSegmentChargePushForce.get())
                .setDefaultValue(2.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaSegmentChargePushForce.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.segment_charge_recoil_force"), NagaConfig.nagaSegmentChargeRecoilForce.get())
                .setDefaultValue(0.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaSegmentChargeRecoilForce.set(value))
                .build());

        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.attack_knockback_force_normal"), NagaConfig.nagaAttackKnockbackForce.get())
                .setDefaultValue(2.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaAttackKnockbackForce.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.attack_knockback_force_enraged"), NagaConfig.nagaAttackKnockbackForceEnraged.get())
                .setDefaultValue(4.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaAttackKnockbackForceEnraged.set(value))
                .build());

        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.naga.segment_damage_transfer_ratio"), NagaConfig.nagaSegmentDamageTransferRatio.get())
                .setDefaultValue(2.0D/3.0D)
                .setMin(0.0D)
                .setMax(1.0D)
                .setSaveConsumer(value -> NagaConfig.nagaSegmentDamageTransferRatio.set(value))
                .build());

        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.naga.can_destroy_blocks"), NagaConfig.nagaCanDestroyBlocks.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> NagaConfig.nagaCanDestroyBlocks.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.naga.can_destroy_blocks_on_charge"), NagaConfig.nagaCanDestroyBlocksOnCharge.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> NagaConfig.nagaCanDestroyBlocksOnCharge.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.death_animation_start_delay"), NagaConfig.nagaDeathAnimationStartDelay.get())
                .setDefaultValue(24)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaDeathAnimationStartDelay.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.naga.death_animation_duration"), NagaConfig.nagaDeathAnimationDuration.get())
                .setDefaultValue(120)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> NagaConfig.nagaDeathAnimationDuration.set(value))
                .build());
                
        return entries;
    }
}