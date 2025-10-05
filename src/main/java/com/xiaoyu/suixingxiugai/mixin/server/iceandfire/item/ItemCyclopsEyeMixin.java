package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.item;

import com.github.alexthe666.iceandfire.item.ItemCyclopsEye;
import com.xiaoyu.suixingxiugai.util.EntityTypeHelper;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static com.xiaoyu.suixingxiugai.config.iceandfire.item.CyclopsEyeConfig.*;

@Mixin(ItemCyclopsEye.class)
public class ItemCyclopsEyeMixin {

    private static final ThreadLocal<Boolean> ABOUT_TO_BREAK = ThreadLocal.withInitial(() -> false);

    @Inject(
        method = "inventoryTick", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"
        )
    )
    private void onAboutToBreak(ItemStack stack, net.minecraft.world.level.Level world,
        net.minecraft.world.entity.Entity entity, int itemSlot, boolean isSelected, CallbackInfo ci) {
        ABOUT_TO_BREAK.set(true);
    }

    @Inject(method = "inventoryTick", at = @At("TAIL"))
    private void onInventoryTickEnd(ItemStack stack, net.minecraft.world.level.Level world,
        net.minecraft.world.entity.Entity entity, int itemSlot, boolean isSelected, CallbackInfo ci) {
        if (ABOUT_TO_BREAK.get() && stack.isEmpty() && entity instanceof LivingEntity) {
            if (cyclopsEyePlayBreakSound.get()) {
                world.playSound(
                    null, entity.getX(), entity.getY(), entity.getZ(),
                    SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F
                );
            }
        }
        ABOUT_TO_BREAK.set(false);
    }

    @ModifyConstant(method = "inventoryTick", constant = @Constant(doubleValue = 15.0))
    private double modifyRange(double original) {
        return cyclopsEyePotionEffectRadius.get();
    }

    @ModifyConstant(method = "inventoryTick", constant = @Constant(intValue = 10))
    private int modifyPotionDuration(int original) {
        return cyclopsEyePotionEffectDuration.get();
    }

    @ModifyConstant(method = "inventoryTick", constant = @Constant(intValue = 1))
    private int modifyPotionLevel(int original) {
        return cyclopsEyePotionEffectLevel.get();
    }

    @ModifyConstant(method = "inventoryTick", constant = @Constant(intValue = 120))
    private int modifyHurtingTicksThreshold(int original) {
        return cyclopsEyeDurabilityReductionSpeed.get();
    }

    @Redirect(
        method = "inventoryTick", 
        at = @At(
            value = "FIELD", 
            target = "Lnet/minecraft/world/effect/MobEffects;WEAKNESS:Lnet/minecraft/world/effect/MobEffect;"
        )
    )
    private MobEffect redirectPotionEffect() {
        return getEffect();
    }

    private MobEffect getEffect() {
        String effectId = cyclopsEyePotionEffectId.get();
        return ForgeRegistries.MOB_EFFECTS.getValue(new ResourceLocation(effectId));
    }

    @Redirect(
        method = "inventoryTick", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"
        )
    )
    private void redirectDurabilityReduction(ItemStack stack, int amount, LivingEntity entity, java.util.function.Consumer<LivingEntity> onBroken) {
        stack.hurtAndBreak(
            cyclopsEyeDurabilityReduction.get(),
            entity,
            onBroken
        );
    }

    @Redirect(
        method = "inventoryTick", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/level/Level;getEntitiesOfClass(Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;"
        )
    )
    private <T extends Entity> java.util.List<T> redirectGetEntitiesOfClass(Level world, Class<T> entityClass, AABB boundingBox) {
        return new java.util.ArrayList<>();
    }

    @Inject(
        method = "inventoryTick", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/level/Level;getEntitiesOfClass(Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;", 
            shift = At.Shift.AFTER
        )
    )
    private void injectCustomLogic(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected,
        CallbackInfo ci) {
        if (!(entity instanceof LivingEntity)) {
            return;
        }

        LivingEntity living = (LivingEntity) entity;
        if (living.getMainHandItem() != stack && living.getOffhandItem() != stack) {
            return;
        }

        String targetType = cyclopsEyeTargetEntityType.get();
        double range = cyclopsEyePotionEffectRadius.get();
        int duration = cyclopsEyePotionEffectDuration.get();
        int level = cyclopsEyePotionEffectLevel.get();
        int minMobs = cyclopsEyeMinAffectedMobs.get();

        Class<? extends Entity> targetClass = getTargetClass(targetType);
        java.util.List<? extends Entity> entities = world.getEntitiesOfClass(targetClass,
            new AABB(
                living.getX() - range, living.getY() - range, living.getZ() - range,
                living.getX() + range, living.getY() + range, living.getZ() + range
            )
        );

        if (entities.size() < minMobs) {
            return;
        }

        MobEffect effect = getEffect();
        boolean inflictedDamage = false;

        for (Entity targetEntity : entities) {
            if (shouldApplyEffect(targetEntity, living, targetType)) {
                if (targetEntity instanceof LivingEntity) {
                    LivingEntity targetLiving = (LivingEntity) targetEntity;
                    targetLiving.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                        effect,
                        duration,
                        level
                    ));
                    inflictedDamage = true;
                }
            }
        }

        if (inflictedDamage && stack.getTag() != null) {
            stack.getTag().putInt("HurtingTicks", stack.getTag().getInt("HurtingTicks") + 1);
        }
    }

    private boolean shouldApplyEffect(Entity targetEntity, LivingEntity holder, String targetType) {
        if (targetEntity == holder) {
            return false;
        }

        if (targetEntity instanceof Mob) {
            Mob targetMob = (Mob) targetEntity;
            if (targetType.equalsIgnoreCase("mob")) {
                return !targetMob.isAlliedTo(holder) && 
                (
                    targetMob.getTarget() == holder || targetMob.getLastHurtByMob() == holder || 
                    targetMob instanceof net.minecraft.world.entity.monster.Enemy
                );
            } else {
                return !targetMob.isAlliedTo(holder);
            }
        }

        return !targetType.equalsIgnoreCase("mob");
    }

    private Class<? extends Entity> getTargetClass(String targetType) {
        return EntityTypeHelper.getTargetClass(targetType);
    }
}