package com.xiaoyu.suixingxiugai;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.lich.LichConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.naga.NagaConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.wand.WandConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.dragon.DragonConfig;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(SuixingXiugai.MOD_ID)
public class SuixingXiugai {
    public static final String MOD_ID = "suixingxiugai";
    public SuixingXiugai() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SuixingxiugaiConfig.SPEC, "suixingxiugai/suixingxiugai-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LichConfig.SPEC, "suixingxiugai/twilightforest/entity/lich/lich-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, NagaConfig.SPEC, "suixingxiugai/twilightforest/entity/naga/naga-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WandConfig.SPEC, "suixingxiugai/twilightforest/item/wand/wand-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DragonConfig.SPEC, "suixingxiugai/iceandfire/entity/dragon/dragon-common.toml");
    }
}