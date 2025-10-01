package com.xiaoyu.suixingxiugai.event.curios.iceandfire;

import com.xiaoyu.suixingxiugai.config.curios.iceandfire.CyclopsEyeConfig;
import com.xiaoyu.suixingxiugai.curios.iceandfire.CyclopsEyeCurios;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CyclopsEyeEventHandler {
    
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            if (CyclopsEyeConfig.cyclopsEyeWorkAsCurio.get()) {
                ResourceLocation cyclopsEyeLocation = new ResourceLocation("iceandfire", "cyclops_eye");
                Item cyclopsEyeItem = ForgeRegistries.ITEMS.getValue(cyclopsEyeLocation);
                
                if (cyclopsEyeItem != null) {
                    CuriosApi.registerCurio(cyclopsEyeItem, new CyclopsEyeCurios());
                }
            }
        });
    }
}