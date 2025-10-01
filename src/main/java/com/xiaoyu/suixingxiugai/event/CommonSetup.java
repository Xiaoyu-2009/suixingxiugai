package com.xiaoyu.suixingxiugai.event;

import com.xiaoyu.suixingxiugai.SuixingXiugai;
import com.xiaoyu.suixingxiugai.command.DragonResurrectionCommand;
import com.xiaoyu.suixingxiugai.command.StatueRestoreCommand;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.IceandfireConfig;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SuixingXiugai.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonSetup {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        if (DragonConfig.enableDragonResurrectionCommand.get()) {
            DragonResurrectionCommand.register(event.getDispatcher());
        }

        if (IceandfireConfig.enableStatueRestoreCommand.get()) {
            StatueRestoreCommand.register(event.getDispatcher());
        }
    }
}