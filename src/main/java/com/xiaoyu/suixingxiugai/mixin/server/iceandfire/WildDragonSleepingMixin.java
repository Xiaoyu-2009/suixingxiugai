package com.xiaoyu.suixingxiugai.mixin.server.iceandfire;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(EntityDragonBase.class)
public abstract class WildDragonSleepingMixin extends Mob {

    protected WildDragonSleepingMixin(EntityType<? extends Mob> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }

    @Inject(
        at = @At("RETURN"), 
        method = "finalizeSpawn(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/world/entity/SpawnGroupData;Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/entity/SpawnGroupData;"
    )
    private void onFinalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        EntityDragonBase dragon = (EntityDragonBase) (Object) this;

        if (!dragon.isTame() && reason != MobSpawnType.BREEDING && reason != MobSpawnType.SPAWN_EGG) {
            if (DragonConfig.wildDragonDefaultSleeping.get()) {
                dragon.setInSittingPose(true);
            } else {
                dragon.setInSittingPose(DragonConfig.wildDragonDefaultSleepingSurface.get());
            }
        }
    }
}