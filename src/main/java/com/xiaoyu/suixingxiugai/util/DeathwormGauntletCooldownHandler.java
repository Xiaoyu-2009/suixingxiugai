package com.xiaoyu.suixingxiugai.util;

import com.xiaoyu.suixingxiugai.config.iceandfire.item.DeathwormGauntletConfig;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class DeathwormGauntletCooldownHandler {
    private static final Map<Player, Integer> cooldownMap = new HashMap<>();

    public static boolean canUseGauntlet(Player player) {
        return !cooldownMap.containsKey(player) || cooldownMap.get(player) <= 0;
    }

    public static void setCooldown(Player player) {
        cooldownMap.put(player, DeathwormGauntletConfig.DEATHWORM_GAUNTLET_COOLDOWN.get());
    }

    public static void tickCooldowns() {
        cooldownMap.entrySet().removeIf(entry -> {
            int cooldown = entry.getValue() - 1;
            if (cooldown <= 0) {
                return true;
            } else {
                entry.setValue(cooldown);
                return false;
            }
        });
    }

    public static int getRemainingCooldown(Player player) {
        return cooldownMap.getOrDefault(player, 0);
    }
}