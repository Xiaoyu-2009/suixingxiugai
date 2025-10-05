package com.xiaoyu.suixingxiugai;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.xiaoyu.suixingxiugai.config.alex.entity.KomodoDragonConfig;
import com.xiaoyu.suixingxiugai.config.alex.item.TendonWhipConfig;
import com.xiaoyu.suixingxiugai.config.bosses_of_mass_destruction.entity.GauntletConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.GazeImmunityConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.IceandfireConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DeathWormConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DreadMobConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.HydraConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.CyclopsEyeConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.DeathwormGauntletConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.GorgonHeadConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.HydraHeartConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.AlphaYetiConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.MinoshroomConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.NagaConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;
import com.xiaoyu.suixingxiugai.client.ClientEvents;
import com.xiaoyu.suixingxiugai.network.NetworkHandler;
import com.xiaoyu.suixingxiugai.event.CriticalHitEventHandler;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;

@Mod(SuixingXiugai.MOD_ID)
public class SuixingXiugai {
    public static final String MOD_ID = "suixingxiugai";
    
    public SuixingXiugai() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SuixingxiugaiConfig.SPEC, "suixingxiugai/suixingxiugai-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TendonWhipConfig.SPEC, "suixingxiugai/alex/item/tendon_whip-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KomodoDragonConfig.SPEC, "suixingxiugai/alex/entity/komodo_dragon-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AlphaYetiConfig.SPEC, "suixingxiugai/twilightforest/entity/alpha_yeti/alpha_yeti-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LichConfig.SPEC, "suixingxiugai/twilightforest/entity/lich/lich-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NagaConfig.SPEC, "suixingxiugai/twilightforest/entity/naga/naga-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MinoshroomConfig.SPEC, "suixingxiugai/twilightforest/entity/minoshroom/minoshroom-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WandConfig.SPEC, "suixingxiugai/twilightforest/item/wand/wand-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DragonConfig.SPEC, "suixingxiugai/iceandfire/entity/dragon/dragon-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GazeImmunityConfig.SPEC, "suixingxiugai/iceandfire/entity/gaze_immunity-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, IceandfireConfig.SPEC, "suixingxiugai/iceandfire/entity/iceandfire-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HydraConfig.SPEC, "suixingxiugai/iceandfire/entity/hydra/hydra-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DreadMobConfig.SPEC, "suixingxiugai/iceandfire/entity/dread/dread-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeathWormConfig.SPEC, "suixingxiugai/iceandfire/entity/deathworm/deathworm-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeathwormGauntletConfig.SPEC, "suixingxiugai/iceandfire/item/deathworm_gauntlet-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HydraHeartConfig.SPEC, "suixingxiugai/iceandfire/item/hydra_heart-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CyclopsEyeConfig.SPEC, "suixingxiugai/iceandfire/item/cyclops_eye-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GorgonHeadConfig.SPEC, "suixingxiugai/iceandfire/item/gorgon_head-common.toml");
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GauntletConfig.SPEC, "suixingxiugai/bosses_of_mass_destruction/entity/gauntlet-common.toml");

        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CyclopsEyeCuriosConfig.SPEC, "suixingxiugai/curios/iceandfire/cyclops_eye-common.toml");
        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HydraHeartCuriosConfig.SPEC, "suixingxiugai/curios/iceandfire/hydra_heart-common.toml");
        
        ClientEvents.PARTICLE_TYPES.register(modEventBus);
        
        NetworkHandler.registerMessages();

        MinecraftForge.EVENT_BUS.addListener(CriticalHitEventHandler::onCriticalHit);
    }
}