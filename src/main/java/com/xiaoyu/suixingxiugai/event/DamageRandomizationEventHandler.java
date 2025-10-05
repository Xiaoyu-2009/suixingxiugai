package com.xiaoyu.suixingxiugai.event;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.xiaoyu.suixingxiugai.util.EntityTypeHelper;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class DamageRandomizationEventHandler {
    private static final Random RANDOM = new Random();
    
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!SuixingxiugaiConfig.enableDamageRandomization.get()) {
            return;
        }

        LivingEntity entity = event.getEntity();

        if (!isEntityEligible(entity)) {
            return;
        }

        float originalDamage = event.getAmount();
        float randomizedDamage = randomizeDamage(originalDamage);
        event.setAmount(randomizedDamage);
    }

    private static boolean isEntityEligible(LivingEntity entity) {
        String entityType = SuixingxiugaiConfig.damageRandomizationEntityTypes.get();
        List<? extends String> entities = SuixingxiugaiConfig.damageRandomizationEntities.get();

        if (!entities.isEmpty()) {
            String entityName = EntityType.getKey(entity.getType()).toString();
            if (entities.contains(entityName)) {
                return true;
            }
        }

        if (!entityType.isEmpty()) {
            return EntityTypeHelper.isEntityOfType(entity, entityType);
        }

        return false;
    }

    private static float randomizeDamage(float originalDamage) {
        double minMultiplier = SuixingxiugaiConfig.damageRandomizationMinMultiplier.get();
        double maxMultiplier = SuixingxiugaiConfig.damageRandomizationMaxMultiplier.get();

        if (minMultiplier > maxMultiplier) {
            double temp = minMultiplier;
            minMultiplier = maxMultiplier;
            maxMultiplier = temp;
        }

        double range = maxMultiplier - minMultiplier;
        double multiplier = minMultiplier + RANDOM.nextDouble() * range;
        
        return (float) (originalDamage * multiplier);
    }
}