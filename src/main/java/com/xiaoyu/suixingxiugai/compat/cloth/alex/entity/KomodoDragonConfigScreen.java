package com.xiaoyu.suixingxiugai.compat.cloth.alex.entity;

import com.xiaoyu.suixingxiugai.config.alex.entity.KomodoDragonConfig;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class KomodoDragonConfigScreen {
    
    public static List<AbstractConfigListEntry> createKomodoDragonConfigEntries(ConfigEntryBuilder entryBuilder) {
        List<AbstractConfigListEntry> entries = new ArrayList<>();
        
        entries.add(entryBuilder.startBooleanToggle(Component.translatable("config.suixingxiugai.alex.entity.komodo_dragon.will_attack"), KomodoDragonConfig.komodoDragonWillAttack.get())
                .setDefaultValue(true)
                .setSaveConsumer(KomodoDragonConfig.komodoDragonWillAttack::set)
                .build());

        return entries;
    }
}