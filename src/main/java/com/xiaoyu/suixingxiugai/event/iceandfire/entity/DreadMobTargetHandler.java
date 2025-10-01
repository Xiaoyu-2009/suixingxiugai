package com.xiaoyu.suixingxiugai.event.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDreadLich;
import com.github.alexthe666.iceandfire.entity.EntityDreadThrall;
import com.github.alexthe666.iceandfire.entity.EntityDreadBeast;
import com.github.alexthe666.iceandfire.entity.EntityDreadGhoul;
import com.github.alexthe666.iceandfire.entity.EntityDreadScuttler;
import com.github.alexthe666.iceandfire.entity.EntityDreadKnight;
import com.github.alexthe666.iceandfire.entity.EntityDreadHorse;
import com.xiaoyu.suixingxiugai.SuixingXiugai;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DreadMobConfig;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SuixingXiugai.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DreadMobTargetHandler {
    
    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity livingEntity = event.getEntity();
        LivingEntity target = event.getOriginalTarget();
        
        if (!(livingEntity instanceof Mob) || target == null) return;

        Mob mob = (Mob) livingEntity;

        if (!DreadMobConfig.dreadMobsAttackAllMobs.get()) {
            event.setCanceled(true);
            return;
        }

        if (mob instanceof EntityDreadLich) {
            if (!DreadMobConfig.dreadLichAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (mob instanceof EntityDreadThrall) {
            if (!DreadMobConfig.dreadThrallAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (mob instanceof EntityDreadBeast) {
            if (!DreadMobConfig.dreadBeastAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (mob instanceof EntityDreadGhoul) {
            if (!DreadMobConfig.dreadGhoulAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (mob instanceof EntityDreadScuttler) {
            if (!DreadMobConfig.dreadScuttlerAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (mob instanceof EntityDreadKnight) {
            if (!DreadMobConfig.dreadKnightAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (mob instanceof EntityDreadHorse) {
            if (!DreadMobConfig.dreadKnightHorseAttackMobs.get()) {
                event.setCanceled(true);
            }
        }
    }
}