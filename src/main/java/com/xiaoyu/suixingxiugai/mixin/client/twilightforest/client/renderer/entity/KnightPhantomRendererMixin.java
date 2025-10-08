package com.xiaoyu.suixingxiugai.mixin.client.twilightforest.client.renderer.entity;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import twilightforest.client.model.entity.KnightPhantomModel;
import twilightforest.client.renderer.entity.KnightPhantomRenderer;
import twilightforest.entity.boss.KnightPhantom;

import java.lang.reflect.Field;
import java.util.List;

@Mixin(KnightPhantomRenderer.class)
public class KnightPhantomRendererMixin {
    
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addArmorLayer(EntityRendererProvider.Context context, KnightPhantomModel model, float shadowSize, CallbackInfo ci) {
        KnightPhantomRenderer renderer = (KnightPhantomRenderer) (Object) this;
        
        try {
            Field layersField = HumanoidMobRenderer.class.getDeclaredField("layers");
            layersField.setAccessible(true);
            
            @SuppressWarnings("unchecked")
            List<RenderLayer<KnightPhantom, KnightPhantomModel>> layers = 
            (List<RenderLayer<KnightPhantom, KnightPhantomModel>>) layersField.get(renderer);

            layers.removeIf(layer -> layer instanceof HumanoidArmorLayer);

            layers.add(new HumanoidArmorLayer<>(renderer, 
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), 
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), 
                context.getModelManager()
            ));
        } catch (Exception e) {
            renderer.addLayer(new HumanoidArmorLayer<>(renderer, 
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), 
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), 
                context.getModelManager()
            ));
        }
    }
}