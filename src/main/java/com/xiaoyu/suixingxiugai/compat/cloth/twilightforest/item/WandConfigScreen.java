package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item;

import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class WandConfigScreen {
    
    public static void createWandConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        
        ConfigCategory wandCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest.wand"));
        
        List<AbstractConfigListEntry> entries = createWandConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : entries) {
            wandCategory.addEntry(entry);
        }
    }
    
    public static List<AbstractConfigListEntry> createWandConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.wand.enable_fortification_targeting"), WandConfig.enableFortificationWandTargeting.get())
                .setDefaultValue(false)
                .setSaveConsumer(value -> WandConfig.enableFortificationWandTargeting.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.wand.fortification_shield_amount"), WandConfig.fortificationWandShieldAmount.get())
                .setDefaultValue(5)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> WandConfig.fortificationWandShieldAmount.set(value))
                .build());
                
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.twilightforest.wand.enable_fortification_invulnerability"), WandConfig.enableFortificationWandInvulnerability.get())
                .setDefaultValue(false)
                .setSaveConsumer(value -> WandConfig.enableFortificationWandInvulnerability.set(value))
                .build());
                
        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.wand.fortification_uses"), WandConfig.fortificationWandUses.get())
                .setDefaultValue(9)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> WandConfig.fortificationWandUses.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.wand.zombie_uses"), WandConfig.zombieWandUses.get())
                .setDefaultValue(9)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> WandConfig.zombieWandUses.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.wand.twilight_uses"), WandConfig.twilightWandUses.get())
                .setDefaultValue(99)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> WandConfig.twilightWandUses.set(value))
                .build());

        entries.add(entryBuilder.startIntField(Component.translatable("config.suixingxiugai.twilightforest.wand.lifedrain_uses"), WandConfig.lifedrainScepterUses.get())
                .setDefaultValue(99)
                .setMin(1)
                .setMax(Integer.MAX_VALUE)
                .setSaveConsumer(value -> WandConfig.lifedrainScepterUses.set(value))
                .build());
                
        return entries;
    }
}