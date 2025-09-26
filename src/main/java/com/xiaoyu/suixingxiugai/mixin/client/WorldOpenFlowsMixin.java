package com.xiaoyu.suixingxiugai.mixin.client;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.mojang.serialization.Lifecycle;

import net.minecraft.client.gui.screens.worldselection.WorldOpenFlows;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WorldOpenFlows.class)
public class WorldOpenFlowsMixin {
    
    @ModifyVariable(method = "confirmWorldCreation", at = @At("HEAD"), argsOnly = true)
    private static Lifecycle alwaysStable(Lifecycle cycle) {
        if (SuixingxiugaiConfig.disableExperimentalWarning.get()) {
            return Lifecycle.stable();
        }
        return cycle;
    }
}