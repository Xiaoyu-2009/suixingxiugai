package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.IafDragonDestructionManager;
import com.xiaoyu.suixingxiugai.util.iceandfire.entity.DragonConfigHelper;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(IafDragonDestructionManager.class)
public class DragonBreathDestroyBlocksMixin {
    
    @Redirect(
        method = "destroyAreaBreath(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lcom/github/alexthe666/iceandfire/entity/EntityDragonBase;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/event/ForgeEventFactory;getMobGriefingEvent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)Z"
        ),
        remap = false
    )
    private static boolean redirectGetMobGriefingEventForBreath(Level level, Entity entity) {
        if (entity instanceof EntityDragonBase dragon) {
            if (dragon.isTame()) {
                boolean canDestroy = DragonConfigHelper.canTamedDragonBreathDestroyBlocks(dragon);
                return canDestroy && ForgeEventFactory.getMobGriefingEvent(level, entity);
            } else {
                boolean canDestroy = DragonConfigHelper.canWildDragonBreathDestroyBlocks(dragon);
                return canDestroy && ForgeEventFactory.getMobGriefingEvent(level, entity);
            }
        }
        return ForgeEventFactory.getMobGriefingEvent(level, entity);
    }
    
    @Redirect(
        method = "destroyAreaCharge(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lcom/github/alexthe666/iceandfire/entity/EntityDragonBase;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/event/ForgeEventFactory;getMobGriefingEvent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)Z"
        ),
        remap = false
    )
    private static boolean redirectGetMobGriefingEventForCharge(Level level, Entity entity) {
        if (entity instanceof EntityDragonBase dragon) {
            if (dragon.isTame()) {
                boolean canDestroy = DragonConfigHelper.canTamedDragonBreathDestroyBlocks(dragon);
                return canDestroy && ForgeEventFactory.getMobGriefingEvent(level, entity);
            } else {
                boolean canDestroy = DragonConfigHelper.canWildDragonBreathDestroyBlocks(dragon);
                return canDestroy && ForgeEventFactory.getMobGriefingEvent(level, entity);
            }
        }
        return ForgeEventFactory.getMobGriefingEvent(level, entity);
    }
}