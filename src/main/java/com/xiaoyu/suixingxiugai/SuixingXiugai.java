package com.xiaoyu.suixingxiugai;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.xiaoyu.suixingxiugai.config.alex.entity.KomodoDragonConfig;
import com.xiaoyu.suixingxiugai.config.alex.item.TendonWhipConfig;
import com.xiaoyu.suixingxiugai.config.bosses_of_mass_destruction.entity.GauntletConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.IceandfireConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.CyclopsEyeConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DreadMobConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.GazeImmunityConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.HydraConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.DeathwormGauntletConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.HydraHeartConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.NagaConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(SuixingXiugai.MOD_ID)
public class SuixingXiugai {
    public static final String MOD_ID = "suixingxiugai";
    public SuixingXiugai() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SuixingxiugaiConfig.SPEC, "suixingxiugai/suixingxiugai-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TendonWhipConfig.SPEC, "suixingxiugai/alex/item/tendon_whip-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KomodoDragonConfig.SPEC, "suixingxiugai/alex/entity/komodo_dragon-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LichConfig.SPEC, "suixingxiugai/twilightforest/entity/lich/lich-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NagaConfig.SPEC, "suixingxiugai/twilightforest/entity/naga/naga-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WandConfig.SPEC, "suixingxiugai/twilightforest/item/wand/wand-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DragonConfig.SPEC, "suixingxiugai/iceandfire/entity/dragon/dragon-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GazeImmunityConfig.SPEC, "suixingxiugai/iceandfire/entity/gaze_immunity-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, IceandfireConfig.SPEC, "suixingxiugai/iceandfire/entity/iceandfire-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HydraConfig.SPEC, "suixingxiugai/iceandfire/entity/hydra/hydra-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DreadMobConfig.SPEC, "suixingxiugai/iceandfire/entity/dread/dread-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DeathwormGauntletConfig.SPEC, "suixingxiugai/iceandfire/item/deathworm_gauntlet-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HydraHeartConfig.SPEC, "suixingxiugai/iceandfire/item/hydra_heart-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CyclopsEyeConfig.SPEC, "suixingxiugai/iceandfire/item/cyclops_eye-common.toml");
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GauntletConfig.SPEC, "suixingxiugai/bosses_of_mass_destruction/entity/gauntlet-common.toml");

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, com.xiaoyu.suixingxiugai.config.curios.iceandfire.CyclopsEyeConfig.SPEC, "suixingxiugai/curios/iceandfire/cyclops_eye-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, com.xiaoyu.suixingxiugai.config.curios.iceandfire.HydraHeartConfig.SPEC, "suixingxiugai/curios/iceandfire/hydra_heart-common.toml");
    }
}