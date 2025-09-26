package com.xiaoyu.suixingxiugai.util.twilightforest.entity;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.naga.NagaConfig;

import twilightforest.entity.boss.Naga;

public class NagaBlockDestructionHelper {
    
    public static boolean canNagaDestroyBlock(Naga naga) {
        if (!NagaConfig.nagaCanDestroyBlocks.get()) {
            return false;
        }
        return true;
    }
    
    public static boolean shouldNagaDestroyAllBlocks(Naga naga) {
        if (!NagaConfig.nagaCanDestroyBlocks.get()) {
            return false;
        }
        return naga.isCharging() || !naga.isMobWithinHomeArea(naga);
    }
}