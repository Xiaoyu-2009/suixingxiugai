package com.xiaoyu.suixingxiugai.event;

import com.xiaoyu.suixingxiugai.SuixingXiugai;
import com.xiaoyu.suixingxiugai.data.PlayerHealthData;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SuixingXiugai.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();

        CompoundTag oldPlayerData = oldPlayer.getPersistentData();
        CompoundTag newPlayerData = newPlayer.getPersistentData();

        if (oldPlayerData.contains("SuixingxiugaiHealthData")) {
            newPlayerData.put("SuixingxiugaiHealthData", 
            oldPlayerData.get("SuixingxiugaiHealthData"));
            PlayerHealthData.loadPlayerHealthData(newPlayer, newPlayerData);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        PlayerHealthData.loadPlayerHealthData(player, player.getPersistentData());
    }
}