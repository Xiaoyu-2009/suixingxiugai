package com.xiaoyu.suixingxiugai.curios.iceandfire;

import com.xiaoyu.suixingxiugai.config.curios.iceandfire.HydraHeartConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class HydraHeartCurios implements ICurioItem {
    
    @Override
    public boolean hasCurioCapability(ItemStack stack) {
        if (!HydraHeartConfig.hydraHeartWorkAsCurio.get()) {
            return false;
        }
        
        Item item = stack.getItem();
        ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(item);
        if (itemKey != null) {
            return "iceandfire:hydra_heart".equals(itemKey.toString());
        }
        
        return false;
    }
}