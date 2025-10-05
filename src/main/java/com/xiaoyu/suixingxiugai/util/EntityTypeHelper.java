package com.xiaoyu.suixingxiugai.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.FlyingMob;

public class EntityTypeHelper {

    public static Class<? extends Entity> getTargetClass(String targetType) {
        switch (targetType.toLowerCase()) {
            case "living":
                return LivingEntity.class;
            case "player":
                return Player.class;
            case "all":
                return Entity.class;
            case "animal":
                return Animal.class;
            case "monster":
                return Monster.class;
            case "ambient":
                return AmbientCreature.class;
            case "water_animal":
                return WaterAnimal.class;
            case "flying":
                return FlyingMob.class;
            case "mob":
            default:
                return Mob.class;
        }
    }

    public static boolean isEntityOfType(LivingEntity entity, String entityType) {
        Class<? extends Entity> targetClass = getTargetClass(entityType);
        return targetClass.isInstance(entity);
    }

    public static boolean isMob(LivingEntity entity) {
        return isEntityOfType(entity, "mob");
    }

    public static boolean isAnimal(LivingEntity entity) {
        return isEntityOfType(entity, "animal");
    }

    public static boolean isPlayer(LivingEntity entity) {
        return isEntityOfType(entity, "player");
    }

    public static boolean isMonster(LivingEntity entity) {
        return isEntityOfType(entity, "monster") && !(entity instanceof Player);
    }
    
    public static boolean matchesEntityType(LivingEntity entity, String entityTypeConfig) {
        return isEntityOfType(entity, entityTypeConfig);
    }
}