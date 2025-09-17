package com.xiaoyu.suixingxiugai;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(SuixingXiugai.MOD_ID)
public class SuixingXiugai {
    public static final String MOD_ID = "suixingxiugai";
    public SuixingXiugai() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SuixingxiugaiConfig.SPEC);
    }
}