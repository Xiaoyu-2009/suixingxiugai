package com.xiaoyu.suixingxiugai.client;

import com.xiaoyu.suixingxiugai.SuixingXiugai;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SuixingXiugai.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientInit {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = 
        DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SuixingXiugai.MOD_ID);
    
    public static final RegistryObject<SimpleParticleType> DAMAGE_NUMBER = 
        PARTICLE_TYPES.register("damage_number", () -> new SimpleParticleType(true));
    
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(DAMAGE_NUMBER.get(), DamageNumberParticle.Factory::new);
    }
}