package com.xiaoyu.suixingxiugai;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.xiaoyu.suixingxiugai.config.alex.entity.*;
import com.xiaoyu.suixingxiugai.config.alex.item.*;
import com.xiaoyu.suixingxiugai.config.bosses_of_mass_destruction.entity.*;
import com.xiaoyu.suixingxiugai.config.cataclysm.item.*;
import com.xiaoyu.suixingxiugai.config.iceandfire.*;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.*;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.*;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.*;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.*;
import com.xiaoyu.suixingxiugai.config.mutantmonsters.entity.*;
import com.xiaoyu.suixingxiugai.client.ClientInit;
import com.xiaoyu.suixingxiugai.network.NetworkHandler;
import com.xiaoyu.suixingxiugai.event.CriticalHitEventHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;

@SuppressWarnings("removal")
@Mod(SuixingXiugai.MOD_ID)
public class SuixingXiugai {
    public static final String MOD_ID = "suixingxiugai";
    
    public SuixingXiugai() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SuixingxiugaiConfig.SPEC, "suixingxiugai/suixingxiugai-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BloodSprayerConfig.SPEC, "suixingxiugai/alex/item/blood_sprayer-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HemolymphBlasterConfig.SPEC, "suixingxiugai/alex/item/hemolymph_blaster-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PocketSandConfig.SPEC, "suixingxiugai/alex/item/pocket_sand-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TendonWhipConfig.SPEC, "suixingxiugai/alex/item/tendon_whip-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KomodoDragonConfig.SPEC, "suixingxiugai/alex/entity/komodo_dragon-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AlphaYetiConfig.SPEC, "suixingxiugai/twilightforest/entity/alpha_yeti/alpha_yeti-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LichConfig.SPEC, "suixingxiugai/twilightforest/entity/lich/lich-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NagaConfig.SPEC, "suixingxiugai/twilightforest/entity/naga/naga-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MinoshroomConfig.SPEC, "suixingxiugai/twilightforest/entity/minoshroom/minoshroom-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KnightPhantomConfig.SPEC, "suixingxiugai/twilightforest/entity/knight_phantom/knight_phantom-common.toml");
        /*ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WandConfig.SPEC, "suixingxiugai/twilightforest/item/wand/wand-common.toml");*/
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FortificationWandConfig.SPEC, "suixingxiugai/twilightforest/item/wand/fortification_wand-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ZombieWandConfig.SPEC, "suixingxiugai/twilightforest/item/wand/zombie_wand-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TwilightWandConfig.SPEC, "suixingxiugai/twilightforest/item/wand/twilight_wand-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LifedrainScepterConfig.SPEC, "suixingxiugai/twilightforest/item/wand/lifedrain_scepter-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DragonConfig.SPEC, "suixingxiugai/iceandfire/entity/dragon/dragon-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DragonDenConfig.SPEC, "suixingxiugai/iceandfire/dragonden-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DragonEggConfig.SPEC, "suixingxiugai/iceandfire/entity/dragon_egg/dragon_egg-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CyclopsConfig.SPEC, "suixingxiugai/iceandfire/entity/cyclops/cyclops-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GazeImmunityConfig.SPEC, "suixingxiugai/iceandfire/entity/gaze_immunity-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, IceandfireConfig.SPEC, "suixingxiugai/iceandfire/entity/iceandfire-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HydraConfig.SPEC, "suixingxiugai/iceandfire/entity/hydra/hydra-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DreadMobConfig.SPEC, "suixingxiugai/iceandfire/entity/dread/dread-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeathWormConfig.SPEC, "suixingxiugai/iceandfire/entity/deathworm/deathworm-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SirenConfig.SPEC, "suixingxiugai/iceandfire/entity/siren/siren-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GorgonConfig.SPEC, "suixingxiugai/iceandfire/entity/gorgon/gorgon-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeathwormGauntletConfig.SPEC, "suixingxiugai/iceandfire/item/deathworm_gauntlet-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HydraHeartConfig.SPEC, "suixingxiugai/iceandfire/item/hydra_heart-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CyclopsEyeConfig.SPEC, "suixingxiugai/iceandfire/item/cyclops_eye-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GorgonHeadConfig.SPEC, "suixingxiugai/iceandfire/item/gorgon_head-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DragonFluteConfig.SPEC, "suixingxiugai/iceandfire/item/dragon_flute-common.toml");
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GauntletConfig.SPEC, "suixingxiugai/bosses_of_mass_destruction/entity/gauntlet-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MeatShredderConfig.SPEC, "suixingxiugai/cataclysm/item/meat_shredder-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MutantZombieConfig.SPEC, "suixingxiugai/mutantmonsters/entity/mutant_zombie-common.toml");

        /* ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CyclopsEyeCuriosConfig.SPEC, "suixingxiugai/curios/iceandfire/cyclops_eye-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HydraHeartCuriosConfig.SPEC, "suixingxiugai/curios/iceandfire/hydra_heart-common.toml"); */
        
        ClientInit.PARTICLE_TYPES.register(modEventBus);
        
        NetworkHandler.registerMessages();

        MinecraftForge.EVENT_BUS.addListener(CriticalHitEventHandler::onCriticalHit);
    }
}