package com.xiaoyu.suixingxiugai.util;

import com.xiaoyu.suixingxiugai.config.twilightforest.entity.naga.NagaConfig;
import twilightforest.entity.boss.Naga;

public class NagaBlockDestructionHelper {
    
    public static boolean canNagaDestroyBlock(Naga naga) {
        // 如果配置禁止娜迦破坏方块，则返回false
        if (!NagaConfig.nagaCanDestroyBlocks.get()) {
            return false;
        }
        // 否则保持原有逻辑
        return true;
    }
    
    public static boolean shouldNagaDestroyAllBlocks(Naga naga) {
        // 如果配置禁止娜迦破坏方块，则返回false
        if (!NagaConfig.nagaCanDestroyBlocks.get()) {
            return false;
        }
        // 否则保持原有逻辑
        return naga.isCharging() || !naga.isMobWithinHomeArea(naga);
    }
}