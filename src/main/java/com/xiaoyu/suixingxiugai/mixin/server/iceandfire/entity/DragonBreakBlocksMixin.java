package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.xiaoyu.suixingxiugai.util.iceandfire.entity.DragonConfigHelper;

import net.minecraft.core.BlockPos;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityDragonBase.class)
public class DragonBreakBlocksMixin {
    
    @Inject(
        method = "breakBlock",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    private void onBreakBlock(BlockPos position, CallbackInfo ci) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;

        if (dragon.isTame()) {
            if (!DragonConfigHelper.canTamedDragonDestroyBlocks(dragon)) {
                ci.cancel();
                return;
            }
        } else {
            if (!DragonConfigHelper.canWildDragonDestroyBlocks(dragon)) {
                ci.cancel();
                return;
            }
        }
    }
}