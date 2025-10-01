package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity;

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
        
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.lich.max_health"), LichConfig.lichMaxHealth.get())
                .setDefaultValue(100.0D)
                .setMin(1.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichMaxHealth.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.lich.movement_speed"), LichConfig.lichMovementSpeed.get())
                .setDefaultValue(0.45D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichMovementSpeed.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.lich.attack_damage"), LichConfig.lichAttackDamage.get())
                .setDefaultValue(3.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichAttackDamage.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.lich.xp_reward"), LichConfig.lichXpReward.get())
                .setDefaultValue(217)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichXpReward.set(value))
                .build());

        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.enable_shield_modification"), LichConfig.enableLichShieldModification.get())
                .setDefaultValue(false)
                .setSaveConsumer(value -> LichConfig.enableLichShieldModification.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.lich.shield_strength"), LichConfig.lichShieldStrength.get())
                .setDefaultValue(6)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichShieldStrength.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.lich.phantom_summon_max_count"), LichConfig.lichPhantomSummonMaxCount.get())
                .setDefaultValue(4)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichPhantomSummonMaxCount.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.phantom_can_be_attacked"), LichConfig.lichPhantomCanBeAttacked.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> LichConfig.lichPhantomCanBeAttacked.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.phantom_drop_chest"), LichConfig.lichPhantomDropChest.get())
                .setDefaultValue(false)
                .setSaveConsumer(value -> LichConfig.lichPhantomDropChest.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.can_spawn_phantom"), LichConfig.lichCanSpawnPhantom.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> LichConfig.lichCanSpawnPhantom.set(value))
                .build());

        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.can_absorb_minions"), LichConfig.lichCanAbsorbMinions.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> LichConfig.lichCanAbsorbMinions.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.can_absorb_mobs_phase2"), LichConfig.lichCanAbsorbMobsInPhase2.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> LichConfig.lichCanAbsorbMobsInPhase2.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.can_absorb_minions_phase2"), LichConfig.lichCanAbsorbMinionsInPhase2.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> LichConfig.lichCanAbsorbMinionsInPhase2.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.can_absorb_mobs_phase3"), LichConfig.lichCanAbsorbMobsInPhase3.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> LichConfig.lichCanAbsorbMobsInPhase3.set(value))
                .build());

        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_mob_absorb_heal_amount"), LichConfig.lichPhase2MobAbsorbHealAmount.get())
                .setDefaultValue(2.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichPhase2MobAbsorbHealAmount.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_mob_absorb_health_threshold"), LichConfig.lichPhase2MobAbsorbHealthThreshold.get())
                .setDefaultValue(50.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichPhase2MobAbsorbHealthThreshold.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_minion_absorb_heal_amount"), LichConfig.lichPhase2MinionAbsorbHealAmount.get())
                .setDefaultValue(10.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichPhase2MinionAbsorbHealAmount.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_minion_absorb_health_threshold"), LichConfig.lichPhase2MinionAbsorbHealthThreshold.get())
                .setDefaultValue(50.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichPhase2MinionAbsorbHealthThreshold.set(value))
                .build());

        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.lich.phase3_mob_absorb_heal_amount"), LichConfig.lichPhase3MobAbsorbHealAmount.get())
                .setDefaultValue(20.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichPhase3MobAbsorbHealAmount.set(value))
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.twilightforest.lich.phase3_mob_absorb_health_threshold"), LichConfig.lichPhase3MobAbsorbHealthThreshold.get())
                .setDefaultValue(80.0D)
                .setMin(0.0D)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(value -> LichConfig.lichPhase3MobAbsorbHealthThreshold.set(value))
                .build());

        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.phase1_can_teleport"), LichConfig.lichPhase1CanTeleport.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> LichConfig.lichPhase1CanTeleport.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.phase2_can_teleport"), LichConfig.lichPhase2CanTeleport.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> LichConfig.lichPhase2CanTeleport.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.lich.phase3_can_teleport"), LichConfig.lichPhase3CanTeleport.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> LichConfig.lichPhase3CanTeleport.set(value))
                .build());
                
        return entries;
    }
}