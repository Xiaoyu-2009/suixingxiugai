package com.xiaoyu.suixingxiugai.event;

import com.xiaoyu.suixingxiugai.util.DeathwormGauntletCooldownHandler;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DeathwormGauntletEventHandler {

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            DeathwormGauntletCooldownHandler.tickCooldowns();
        }
    }
}