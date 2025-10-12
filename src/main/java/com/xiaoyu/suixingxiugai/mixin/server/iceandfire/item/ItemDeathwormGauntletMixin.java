package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.item;

import com.github.alexthe666.iceandfire.item.ItemDeathwormGauntlet;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.DeathwormGauntletConfig;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemDeathwormGauntlet.class)
public abstract class ItemDeathwormGauntletMixin {

    @Shadow
    private boolean deathwormReceded;

    @Shadow
    private boolean deathwormLaunched;

    @Shadow
    private int specialDamage;

    @Overwrite
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
        if (!deathwormReceded && !deathwormLaunched) {
            if (entity instanceof Player player) {
                CompoundTag tag = stack.getOrCreateTag();

                if (tag.getInt("HolderID") != player.getId()) {
                    tag.putInt("HolderID", player.getId());
                }

                if (player.getCooldowns().getCooldownPercent(stack.getItem(), 0.0F) == 0) {
                    player.getCooldowns().addCooldown(stack.getItem(), DeathwormGauntletConfig.deathwormGauntletCooldown.get());
                    player.playSound(com.github.alexthe666.iceandfire.misc.IafSoundRegistry.DEATHWORM_ATTACK, 1F, 1F);
                    deathwormReceded = false;
                    deathwormLaunched = true;
                }
            }
        }
    }

    @Overwrite
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entity, int timeLeft) {
        if (specialDamage > 0) {
            stack.hurtAndBreak(specialDamage, entity, player -> player.broadcastBreakEvent(entity.getUsedItemHand()));
            specialDamage = 0;
        }

        CompoundTag tag = stack.getOrCreateTag();
        if (tag.getInt("HolderID") != -1) {
            tag.putInt("HolderID", -1);
        }
    }

    @Overwrite
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
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
                    tempLungeTicks = 20;
                    deathwormReceded = true;
                }
            }

            if (data.miscData.lungeTicks == 20) {
                if (entity instanceof Player player) {
                    if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                        Vec3 vector3d = player.getViewVector(1.0F).normalize();
                        double range = DeathwormGauntletConfig.deathwormGauntletRange.get();

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
                                DeathwormGauntletConfig.deathwormGauntletDamage.get().floatValue());

                                player.getCooldowns().addCooldown(stack.getItem(), DeathwormGauntletConfig.deathwormGauntletCooldown.get());

                                double pullForce = DeathwormGauntletConfig.deathwormGauntletPullForce.get();

                                if (DeathwormGauntletConfig.deathwormGauntletKnockbackResistanceReduction.get()) {
                                    pullForce *= (1.0 - Math.min(1.0, livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE)));
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
            }

            data.miscData.setLungeTicks(tempLungeTicks);
        });
    }
}