package com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.item;

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
        
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.damage"), DeathwormGauntletConfig.DEATHWORM_GAUNTLET_DAMAGE.get())
                .setDefaultValue(3.0)
                .setMin(0.0)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(DeathwormGauntletConfig.DEATHWORM_GAUNTLET_DAMAGE::set)
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.range"), DeathwormGauntletConfig.DEATHWORM_GAUNTLET_RANGE.get())
                .setDefaultValue(5.0)
                .setMin(0.0)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(DeathwormGauntletConfig.DEATHWORM_GAUNTLET_RANGE::set)
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.cooldown"), DeathwormGauntletConfig.DEATHWORM_GAUNTLET_COOLDOWN.get())
                .setDefaultValue(12)
                .setMin(0)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(DeathwormGauntletConfig.DEATHWORM_GAUNTLET_COOLDOWN::set)
                .build());
                
        entries.add(entryBuilder.startDoubleField(Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.pull_force"), DeathwormGauntletConfig.DEATHWORM_GAUNTLET_PULL_FORCE.get())
                .setDefaultValue(0.5)
                .setMin(0.0)
                .setMax(Double.MAX_VALUE)
                .setSaveConsumer(DeathwormGauntletConfig.DEATHWORM_GAUNTLET_PULL_FORCE::set)
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.iceandfire.item.deathworm_gauntlet.knockback_resistance_reduction"), DeathwormGauntletConfig.DEATHWORM_GAUNTLET_KNOCKBACK_RESISTANCE_REDUCTION.get())
                .setDefaultValue(true)
                .setSaveConsumer(DeathwormGauntletConfig.DEATHWORM_GAUNTLET_KNOCKBACK_RESISTANCE_REDUCTION::set)
                .build());

        return entries;
    }
}