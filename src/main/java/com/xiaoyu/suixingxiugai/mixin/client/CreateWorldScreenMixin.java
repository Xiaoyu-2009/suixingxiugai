package com.xiaoyu.suixingxiugai.mixin.client;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {
    @ModifyVariable(method = "tryApplyNewDataPacks", at = @At("HEAD"), argsOnly = true)
    public boolean dontShowWarning(boolean showWarning) {
        if (SuixingxiugaiConfig.disableExperimentalWarning.get()) {
            return false;
        }
        return showWarning;
    }
}