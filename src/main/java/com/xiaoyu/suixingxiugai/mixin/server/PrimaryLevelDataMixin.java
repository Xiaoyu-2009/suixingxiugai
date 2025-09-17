package com.xiaoyu.suixingxiugai.mixin.server;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;

import net.minecraft.world.level.storage.PrimaryLevelData;

@Mixin(value = PrimaryLevelData.class, remap = false)
public class PrimaryLevelDataMixin {
    @Inject(method = "hasConfirmedExperimentalWarning", at = @At("HEAD"), cancellable = true)
    public void hasConfirmedExperimentalWarning(CallbackInfoReturnable<Boolean> cir) {
        if (SuixingxiugaiConfig.disableExperimentalWarning.get()) {
            cir.setReturnValue(true);
        }
    }
}