package com.xiaoyu.suixingxiugai.event.curios.iceandfire;

import com.xiaoyu.suixingxiugai.config.curios.iceandfire.HydraHeartConfig;
import com.xiaoyu.suixingxiugai.curios.iceandfire.HydraHeartCurios;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HydraHeartEventHandler {
    
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if (HydraHeartConfig.hydraHeartWorkAsCurio.get()) {
                ResourceLocation hydraHeartLocation = new ResourceLocation("iceandfire", "hydra_heart");
                Item hydraHeartItem = ForgeRegistries.ITEMS.getValue(hydraHeartLocation);
                
                if (hydraHeartItem != null) {
                    CuriosApi.registerCurio(hydraHeartItem, new HydraHeartCurios());
                }
            }
        });
    }
}