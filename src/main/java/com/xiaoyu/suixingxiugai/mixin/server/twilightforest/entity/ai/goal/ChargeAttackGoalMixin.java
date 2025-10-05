package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.entity.ai.goal;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.MinoshroomConfig;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraftforge.event.ForgeEventFactory;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import twilightforest.entity.ai.goal.ChargeAttackGoal;
import twilightforest.entity.boss.Minoshroom;

@Mixin(ChargeAttackGoal.class)
public class ChargeAttackGoalMixin {
    
    @Shadow
    private PathfinderMob charger;
    
    @ModifyConstant(method = "start", constant = @Constant(intValue = 15))
    private int modifyBaseRunningChargeTime(int original) {
        return MinoshroomConfig.minoshroomChargeTime.get();
    }
    
    @Redirect(
        method = "tick", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraftforge/event/ForgeEventFactory;getMobGriefingEvent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)Z"
        )
    )
    private boolean redirectMobGriefingEvent(net.minecraft.world.level.Level level, net.minecraft.world.entity.Entity entity) {
        if (this.charger instanceof Minoshroom && !MinoshroomConfig.minoshroomChargeCanBreakBlocks.get()) {
            return false;
        }
        return ForgeEventFactory.getMobGriefingEvent(level, entity);
    }
}