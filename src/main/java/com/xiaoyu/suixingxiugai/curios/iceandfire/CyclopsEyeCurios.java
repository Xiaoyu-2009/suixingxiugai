package com.xiaoyu.suixingxiugai.curios.iceandfire;

import com.xiaoyu.suixingxiugai.config.curios.iceandfire.CyclopsEyeConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class CyclopsEyeCurios implements ICurioItem {
    
    @Override
    public boolean hasCurioCapability(ItemStack stack) {
        if (!CyclopsEyeConfig.cyclopsEyeWorkAsCurio.get()) {
            return false;
        }
        
        Item item = stack.getItem();
        ResourceLocation itemKey = ForgeRegistries.ITEMS.getKey(item);
        if (itemKey != null) {
            return "iceandfire:cyclops_eye".equals(itemKey.toString());
        }
        
        return false;
    }
}