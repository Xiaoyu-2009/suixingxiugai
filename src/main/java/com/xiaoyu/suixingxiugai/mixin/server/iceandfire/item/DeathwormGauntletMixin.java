package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.item;

import com.github.alexthe666.iceandfire.item.ItemDeathwormGauntlet;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.DeathwormGauntletConfig;
import com.xiaoyu.suixingxiugai.util.DeathwormGauntletCooldownHandler;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemDeathwormGauntlet.class)
public abstract class DeathwormGauntletMixin {

    @Shadow
    private boolean deathwormReceded;

    @Shadow
    private boolean deathwormLaunched;

    @Shadow
    private int specialDamage;

    @Overwrite
    public void inventoryTick(ItemStack stack, Level world, net.minecraft.world.entity.Entity entity, int itemSlot, boolean isSelected) {
        if (!(entity instanceof LivingEntity)) {
            return;
        }

        com.github.alexthe666.iceandfire.entity.props.EntityDataProvider.getCapability(entity).ifPresent(data -> {
            int tempLungeTicks = data.miscData.lungeTicks;

            if (deathwormReceded) {
                if (tempLungeTicks > 0) {
                    tempLungeTicks = tempLungeTicks - 4;
                }

                if (tempLungeTicks <= 0) {
                    tempLungeTicks = 0;
                    deathwormReceded = false;
                    deathwormLaunched = false;
                }
            } else if (deathwormLaunched) {
                tempLungeTicks = 4 + tempLungeTicks;

                if (tempLungeTicks > 20) {
                    deathwormReceded = true;
                }
            }

            if (data.miscData.lungeTicks == 20) {
                if (entity instanceof Player player) {
                    if (!DeathwormGauntletCooldownHandler.canUseGauntlet(player)) {
                        data.miscData.setLungeTicks(tempLungeTicks);
                        return;
                    }

                    Vec3 vector3d = player.getViewVector(1.0F).normalize();
                    double range = DeathwormGauntletConfig.DEATHWORM_GAUNTLET_RANGE.get();

                    for (LivingEntity livingEntity : world.getEntitiesOfClass(LivingEntity.class, new AABB(player.getX() - range, player.getY() - range, player.getZ() - range, player.getX() + range, player.getY() + range, player.getZ() + range))) {
                        if (livingEntity == entity) {
                            continue;
                        }

                        Vec3 vector3d1 = new Vec3(livingEntity.getX() - player.getX(), livingEntity.getY() - player.getY(), livingEntity.getZ() - player.getZ());
                        double d0 = vector3d1.length();
                        vector3d1 = vector3d1.normalize();
                        double d1 = vector3d.dot(vector3d1);
                        boolean canSee = d1 > 1.0D - 0.5D / d0 && player.hasLineOfSight(livingEntity);

                        if (canSee) {
                            specialDamage++;

                            livingEntity.hurt(entity.level().damageSources().playerAttack((Player) entity),
                            DeathwormGauntletConfig.DEATHWORM_GAUNTLET_DAMAGE.get().floatValue());

                            DeathwormGauntletCooldownHandler.setCooldown(player);

                            double pullForce = DeathwormGauntletConfig.DEATHWORM_GAUNTLET_PULL_FORCE.get();

                            if (DeathwormGauntletConfig.DEATHWORM_GAUNTLET_KNOCKBACK_RESISTANCE_REDUCTION.get()) {
                                pullForce *= (1.0 - Math.min(1.0, livingEntity.getAttributeValue(net.minecraft.world.entity.ai.attributes.Attributes.KNOCKBACK_RESISTANCE)));
                            }

                            livingEntity.push(
                                (player.getX() - livingEntity.getX()) * pullForce,
                                (player.getY() - livingEntity.getY()) * pullForce,
                                (player.getZ() - livingEntity.getZ()) * pullForce
                            );
                        }
                    }
                }
            }

            data.miscData.setLungeTicks(tempLungeTicks);
        });
    }
}