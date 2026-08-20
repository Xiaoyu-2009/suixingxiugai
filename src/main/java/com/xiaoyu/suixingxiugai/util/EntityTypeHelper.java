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
        return switch (targetType.toLowerCase()) {
            case "living" -> LivingEntity.class;
            case "player" -> Player.class;
            case "all" -> Entity.class;
            case "animal" -> Animal.class;
            case "monster" -> Monster.class;
            case "ambient" -> AmbientCreature.class;
            case "water_animal" -> WaterAnimal.class;
            case "flying" -> FlyingMob.class;
            default -> Mob.class;
        };
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