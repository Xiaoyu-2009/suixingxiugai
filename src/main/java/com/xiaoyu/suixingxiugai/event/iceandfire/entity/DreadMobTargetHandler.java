package com.xiaoyu.suixingxiugai.event.iceandfire.entity;

import com.xiaoyu.suixingxiugai.SuixingXiugai;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DreadMobConfig;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SuixingXiugai.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DreadMobTargetHandler {

    private static boolean initialized = false;
    private static Class<?> dreadLichClass = null;
    private static Class<?> dreadThrallClass = null;
    private static Class<?> dreadBeastClass = null;
    private static Class<?> dreadGhoulClass = null;
    private static Class<?> dreadScuttlerClass = null;
    private static Class<?> dreadKnightClass = null;
    private static Class<?> dreadHorseClass = null;

    private static void initialize() {
        if (initialized) {
            return;
        }
        
        try {
            dreadLichClass = Class.forName("com.github.alexthe666.iceandfire.entity.EntityDreadLich");
            dreadThrallClass = Class.forName("com.github.alexthe666.iceandfire.entity.EntityDreadThrall");
            dreadBeastClass = Class.forName("com.github.alexthe666.iceandfire.entity.EntityDreadBeast");
            dreadGhoulClass = Class.forName("com.github.alexthe666.iceandfire.entity.EntityDreadGhoul");
            dreadScuttlerClass = Class.forName("com.github.alexthe666.iceandfire.entity.EntityDreadScuttler");
            dreadKnightClass = Class.forName("com.github.alexthe666.iceandfire.entity.EntityDreadKnight");
            dreadHorseClass = Class.forName("com.github.alexthe666.iceandfire.entity.EntityDreadHorse");
        } catch (ClassNotFoundException | ExceptionInInitializerError e) {}

        initialized = true;
    }
    
    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        initialize();

        if (dreadLichClass == null) {
            return;
        }
        
        LivingEntity livingEntity = event.getEntity();
        LivingEntity target = event.getOriginalTarget();
        
        if (!(livingEntity instanceof Mob) || target == null) return;

        Mob mob = (Mob) livingEntity;

        if (!DreadMobConfig.dreadMobsAttackAllMobs.get()) {
            event.setCanceled(true);
            return;
        }

        if (dreadLichClass != null && dreadLichClass.isInstance(mob)) {
            if (!DreadMobConfig.dreadLichAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (dreadThrallClass != null && dreadThrallClass.isInstance(mob)) {
            if (!DreadMobConfig.dreadThrallAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (dreadBeastClass != null && dreadBeastClass.isInstance(mob)) {
            if (!DreadMobConfig.dreadBeastAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (dreadGhoulClass != null && dreadGhoulClass.isInstance(mob)) {
            if (!DreadMobConfig.dreadGhoulAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (dreadScuttlerClass != null && dreadScuttlerClass.isInstance(mob)) {
            if (!DreadMobConfig.dreadScuttlerAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (dreadKnightClass != null && dreadKnightClass.isInstance(mob)) {
            if (!DreadMobConfig.dreadKnightAttackMobs.get()) {
                event.setCanceled(true);
            }
        } else if (dreadHorseClass != null && dreadHorseClass.isInstance(mob)) {
            if (!DreadMobConfig.dreadKnightHorseAttackMobs.get()) {
                event.setCanceled(true);
            }
        }
    }
}