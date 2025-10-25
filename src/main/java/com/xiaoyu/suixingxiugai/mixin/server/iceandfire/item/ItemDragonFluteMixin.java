package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.item;

import com.github.alexthe666.iceandfire.item.ItemDragonFlute;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.DragonFluteConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemDragonFlute.class)
public class ItemDragonFluteMixin {
    
    @ModifyArg(
        method = "use",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemCooldowns;addCooldown(Lnet/minecraft/world/item/Item;I)V"
        ),
        index = 1
    )
    private int modifyDragonFluteCooldown(int originalCooldown) {
        return DragonFluteConfig.dragonFluteCooldown.get();
    }
    
    @ModifyVariable(
        method = "use",
        at = @At(
            value = "STORE"
        ),
        ordinal = 0
    )
    private float modifyDragonFluteDistance(float chunksize) {
        return 16 * DragonFluteConfig.dragonFluteDistance.get();
    }
}