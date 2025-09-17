package com.xiaoyu.suixingxiugai.gamerule;

import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SuixingxiugaiGameRules {
    public static final GameRules.Key<GameRules.BooleanValue> ICE_AND_FIRE_DRAGON_PVP =
        GameRules.register("iceAndFireDragonPVP", GameRules.Category.PLAYER,
        GameRules.BooleanValue.create(false)
    );

    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {}
}