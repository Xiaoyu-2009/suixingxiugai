package com.xiaoyu.suixingxiugai.util.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityFireDragon;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.entity.EntityLightningDragon;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;

import java.util.function.Supplier;

public class DragonConfigHelper {

    public static boolean canTamedDragonDestroyBlocks(EntityDragonBase dragon) {
        return getDragonTypeConfig(
            dragon, 
            DragonConfig.tamedFireDragonDestroyBlocks::get,
            DragonConfig.tamedIceDragonDestroyBlocks::get,
            DragonConfig.tamedLightningDragonDestroyBlocks::get
        );
    }

    public static boolean canWildDragonDestroyBlocks(EntityDragonBase dragon) {
        return getDragonTypeConfig(
            dragon, 
            DragonConfig.fireDragonDestroyBlocks::get,
            DragonConfig.iceDragonDestroyBlocks::get,
            DragonConfig.lightningDragonDestroyBlocks::get
        );
    }

    public static boolean canTamedDragonBreathDestroyBlocks(EntityDragonBase dragon) {
        return getDragonTypeConfig(
            dragon,
            DragonConfig.tamedFireDragonBreathDestroyBlocks::get,
            DragonConfig.tamedIceDragonBreathDestroyBlocks::get,
            DragonConfig.tamedLightningDragonBreathDestroyBlocks::get
        );
    }

    public static boolean canWildDragonBreathDestroyBlocks(EntityDragonBase dragon) {
        return getDragonTypeConfig(
            dragon, 
            DragonConfig.fireDragonBreathDestroyBlocks::get,
            DragonConfig.iceDragonBreathDestroyBlocks::get,
            DragonConfig.lightningDragonBreathDestroyBlocks::get
        );
    }

    public static boolean canDragonBreath(EntityDragonBase dragon) {
        if (dragon.isTame()) {
            return getDragonTypeConfig(
                dragon, 
                DragonConfig.tamedFireDragonCanBreath::get,
                DragonConfig.tamedIceDragonCanBreath::get,
                DragonConfig.tamedLightningDragonCanBreath::get
            );
        } else {
            return getDragonTypeConfig(
                dragon, 
                DragonConfig.fireDragonCanBreath::get,
                DragonConfig.iceDragonCanBreath::get,
                DragonConfig.lightningDragonCanBreath::get
            );
        }
    }

    private static boolean getDragonTypeConfig(
        EntityDragonBase dragon, Supplier<Boolean> fireConfig,
        Supplier<Boolean> iceConfig, Supplier<Boolean> lightningConfig
    ) {
        if (dragon instanceof EntityFireDragon) {
            return fireConfig.get();
        } else if (dragon instanceof EntityIceDragon) {
            return iceConfig.get();
        } else if (dragon instanceof EntityLightningDragon) {
            return lightningConfig.get();
        }
        return false;
    }
}