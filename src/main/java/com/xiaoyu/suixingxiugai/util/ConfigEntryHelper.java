package com.xiaoyu.suixingxiugai.util;

import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import net.minecraft.network.chat.Component;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConfigEntryHelper {

    public static AbstractConfigListEntry<?> createIntField(
        ConfigEntryBuilder entryBuilder,
        Component text,
        ForgeConfigSpec.IntValue configValue,
        int defaultValue,
        int min,
        int max
    ) {
        return entryBuilder.startIntField(text, configValue.get())
        .setDefaultValue(defaultValue)
        .setMin(min)
        .setMax(max)
        .setSaveConsumer(configValue::set)
        .build();
    }

    public static AbstractConfigListEntry<?> createDoubleField(
        ConfigEntryBuilder entryBuilder,
        Component text,
        ForgeConfigSpec.DoubleValue configValue,
        double defaultValue,
        double min,
        double max
    ) {
        return entryBuilder.startDoubleField(text, configValue.get())
        .setDefaultValue(defaultValue)
        .setMin(min)
        .setMax(max)
        .setSaveConsumer(configValue::set)
        .build();
    }

    public static AbstractConfigListEntry<?> createBooleanToggle(
        ConfigEntryBuilder entryBuilder,
        Component text,
        ForgeConfigSpec.BooleanValue configValue,
        boolean defaultValue
    ) {
        return entryBuilder.startBooleanToggle(text, configValue.get())
        .setDefaultValue(defaultValue)
        .setSaveConsumer(configValue::set)
        .build();
    }

    public static AbstractConfigListEntry<?> createStringField(
        ConfigEntryBuilder entryBuilder,
        Component text,
        ForgeConfigSpec.ConfigValue<String> configValue,
        String defaultValue
    ) {
        return entryBuilder.startStrField(text, configValue.get())
        .setDefaultValue(defaultValue)
        .setSaveConsumer(configValue::set)
        .build();
    }

    public static AbstractConfigListEntry<?> createStringDropdownMenu(
        ConfigEntryBuilder entryBuilder,
        Component text,
        ForgeConfigSpec.ConfigValue<String> configValue,
        String defaultValue,
        List<String> selections
    ) {
        return entryBuilder.startStringDropdownMenu(text, configValue.get())
        .setDefaultValue(defaultValue)
        .setSelections(selections)
        .setSaveConsumer(configValue::set)
        .build();
    }

    public static AbstractConfigListEntry<?> createStringList(
        ConfigEntryBuilder entryBuilder,
        Component text,
        ForgeConfigSpec.ConfigValue<List<? extends String>> configValue,
        List<String> defaultValue
    ) {
        List<String> listCopy = new ArrayList<>(configValue.get());
        return entryBuilder.startStrList(text, listCopy)
        .setDefaultValue(defaultValue)
        .setSaveConsumer(value -> configValue.set(new ArrayList<>(value)))
        .build();
    }

    public static AbstractConfigListEntry<?> createDoubleList(
        ConfigEntryBuilder entryBuilder,
        Component text,
        List<Double> currentValue,
        List<Double> defaultValue,
        Consumer<List<Double>> saveConsumer
    ) {
        List<Double> listCopy = new ArrayList<>(currentValue);
        return entryBuilder.startDoubleList(text, listCopy)
        .setDefaultValue(defaultValue)
        .setSaveConsumer(saveConsumer)
        .build();
    }

    public static AbstractConfigListEntry<?> createIntList(
        ConfigEntryBuilder entryBuilder,
        Component text,
        List<Integer> currentValue,
        List<Integer> defaultValue,
        Consumer<List<Integer>> saveConsumer
    ) {
        List<Integer> listCopy = new ArrayList<>(currentValue);
        return entryBuilder.startIntList(text, listCopy)
        .setDefaultValue(defaultValue)
        .setSaveConsumer(saveConsumer)
        .build();
    }
}