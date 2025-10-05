package com.xiaoyu.suixingxiugai.event.bosses_of_mass_destruction;

import com.xiaoyu.suixingxiugai.config.bosses_of_mass_destruction.entity.GauntletConfig;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class GauntletEventHandler {

    private static final WeakHashMap<LivingEntity, Float> lastHealthMap = new WeakHashMap<>();
    /* private static final WeakHashMap<LivingEntity, Long> lastHealTimeMap = new WeakHashMap<>(); */
    
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (isGauntletEntity(entity)) {
            lastHealthMap.put(entity, entity.getHealth());
        }
    }
    
    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();

        if (isGauntletEntity(entity)) {
            if (!GauntletConfig.gauntletIdleHealEnabled.get()) {
                event.setCanceled(true);
                return;
            }

/*             long currentTime = entity.level().getGameTime();
            Long lastHealTime = lastHealTimeMap.get(entity);
            
            if (lastHealTime == null || currentTime - lastHealTime >= GauntletConfig.gauntletIdleHealInterval.get()) {
                event.setCanceled(true);

                if (entity instanceof Mob mob && mob.getTarget() == null && entity.isAlive()) {
                    float healAmount = GauntletConfig.gauntletIdleHealAmount.get().floatValue();
                    entity.heal(healAmount);
                    lastHealTimeMap.put(entity, currentTime);
                }
            } */
        }
    }
    
    private static boolean isGauntletEntity(LivingEntity entity) {
        return entity.getClass().getSimpleName().equals("GauntletEntity") && 
        entity.getClass().getPackageName().contains("bosses_of_mass_destruction");
    }
}