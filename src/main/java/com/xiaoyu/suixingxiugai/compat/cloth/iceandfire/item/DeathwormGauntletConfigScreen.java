package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item;

import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.DeathwormGauntletConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DeathwormGauntletConfigScreen {
    
    public static List<AbstractConfigListEntry> createDeathwormGauntletConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.damage"),
                DeathwormGauntletConfig.deathwormGauntletDamage,
                3.0,
                0.0,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.range"),
                DeathwormGauntletConfig.deathwormGauntletRange,
                5.0,
                0.0,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createIntField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.cooldown"),
                DeathwormGauntletConfig.deathwormGauntletCooldown,
                12,
                0,
                Integer.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.pull_force"),
                DeathwormGauntletConfig.deathwormGauntletPullForce,
                0.5,
                0.0,
                Double.MAX_VALUE
        ));
                
        entries.add(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.knockback_resistance_reduction"),
                DeathwormGauntletConfig.deathwormGauntletKnockbackResistanceReduction,
                true
        ));

        return entries;
    }
}