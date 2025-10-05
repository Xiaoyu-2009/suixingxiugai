package com.xiaoyu.suixingxiugai.event;

import com.xiaoyu.suixingxiugai.SuixingXiugai;
import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SuixingXiugai.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GameRuleEventHandler {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (SuixingxiugaiConfig.enableKeepInventoryOnEnterWorld.get()) {
            if (!event.getEntity().level().isClientSide() && event.getEntity().level().getServer() != null) {
                MinecraftServer server = event.getEntity().level().getServer();
                server.getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).set(true, server);
            }
        }
    }
}