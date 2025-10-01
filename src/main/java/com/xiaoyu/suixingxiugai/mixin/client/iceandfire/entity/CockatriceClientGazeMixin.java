package com.xiaoyu.suixingxiugai.mixin.client.iceandfire.entity;

import com.github.alexthe666.iceandfire.client.render.entity.RenderCockatrice;
import com.github.alexthe666.iceandfire.entity.EntityCockatrice;
import com.xiaoyu.suixingxiugai.util.iceandfire.entity.GazeImmunityHelper;

import net.minecraft.world.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderCockatrice.class)
public class CockatriceClientGazeMixin {
    
    @Inject(
        method = "render(Lcom/github/alexthe666/iceandfire/entity/EntityCockatrice;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", 
        at = @At(
            value = "INVOKE", 
            target = "Lcom/github/alexthe666/iceandfire/client/particle/CockatriceBeamRender;render(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;F)V"
        ), 
        cancellable = true, 
        remap = false
    )
    private void onRender(
        EntityCockatrice entity, float entityYaw, float partialTicks, 
        com.mojang.blaze3d.vertex.PoseStack matrixStack, 
        net.minecraft.client.renderer.MultiBufferSource bufferIn, 
        int packedLightIn, CallbackInfo ci
    ) {
        LivingEntity target = entity.getTargetedEntity();

        if (target != null && GazeImmunityHelper.isImmuneToGazeAttack(target)) {
            ci.cancel();
        }
    }
}