package com.xiaoyu.suixingxiugai.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class PlayerHealthData {
    private static final String HEALTH_DATA_TAG = "SuixingxiugaiHealthData";
    private static final String MAX_HEALTH_BASE_VALUE_TAG = "MaxHealthBaseValue";

    public static void savePlayerHealthData(Player player, CompoundTag tag) {
        AttributeInstance maxHealthAttribute = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            CompoundTag healthData = new CompoundTag();
            healthData.putDouble(MAX_HEALTH_BASE_VALUE_TAG, maxHealthAttribute.getBaseValue());
            tag.put(HEALTH_DATA_TAG, healthData);
        }
    }

    public static void loadPlayerHealthData(Player player, CompoundTag tag) {
        if (tag.contains(HEALTH_DATA_TAG)) {
            CompoundTag healthData = tag.getCompound(HEALTH_DATA_TAG);
            if (healthData.contains(MAX_HEALTH_BASE_VALUE_TAG)) {
                double maxHealthBaseValue = healthData.getDouble(MAX_HEALTH_BASE_VALUE_TAG);
                AttributeInstance maxHealthAttribute = player.getAttribute(Attributes.MAX_HEALTH);
                if (maxHealthAttribute != null) {
                    maxHealthAttribute.setBaseValue(maxHealthBaseValue);
                }
            }
        }
    }
}