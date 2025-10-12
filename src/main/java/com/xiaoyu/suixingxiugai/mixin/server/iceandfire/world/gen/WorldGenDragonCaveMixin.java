package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.world.gen;

import com.github.alexthe666.iceandfire.world.gen.WorldGenDragonCave;
import com.xiaoyu.suixingxiugai.config.iceandfire.DragonDenConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldGenDragonCave.class)
public class WorldGenDragonCaveMixin {

    private static final ThreadLocal<RandomSource> RANDOM_HOLDER = new ThreadLocal<>();
    
    @ModifyArg(
        method = "createDragon", 
        at = @At(
            value = "INVOKE", 
            target = "Lcom/github/alexthe666/iceandfire/entity/EntityDragonBase;setInSittingPose(Z)V"
        ), 
        index = 0
    )
    private boolean modifySetInSittingPoseArg(boolean sleeping) {
        return DragonConfig.wildDragonDefaultSleepingUnderground.get();
    }

    @Redirect(
        method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
        at = @At(
            value = "NEW",
            target = "(III)Lnet/minecraft/core/BlockPos;"
        )
    )
    private BlockPos redirectBlockPosConstructor(int x, int y, int z) {
        int minY = DragonDenConfig.undergroundDragonDenMinYLevel.get();
        int maxY = DragonDenConfig.undergroundDragonDenMaxYLevel.get();

        int modifiedY = y;
        if (y < minY) {
            modifiedY = minY;
        } else if (y > maxY) {
            modifiedY = maxY;
        }

        return new BlockPos(x, modifiedY, z);
    }

    @Inject(
        method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
        at = @At("HEAD")
    )
    private void storeRandomSource(FeaturePlaceContext<NoneFeatureConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        RANDOM_HOLDER.set(context.random());
    }

    @Inject(
        method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
        at = @At("RETURN")
    )
    private void clearRandomSource(FeaturePlaceContext<NoneFeatureConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        RANDOM_HOLDER.remove();
    }

    @ModifyVariable(
        method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/WorldGenLevel;getMinBuildHeight()I",
            shift = At.Shift.BEFORE
        ),
        ordinal = 1
    )
    private int modifyFinalJValue(int j) {
        if (DragonDenConfig.undergroundDragonDenCanGenerateInMidAir.get()) {
            int minY = DragonDenConfig.undergroundDragonDenMinYLevel.get();
            int maxY = DragonDenConfig.undergroundDragonDenMaxYLevel.get();
            
            RandomSource random = RANDOM_HOLDER.get();
            if (random != null && minY <= maxY) {
                int baseY = minY + random.nextInt(maxY - minY + 1);
                int offsetY = 20 + random.nextInt(30);
                return baseY - offsetY;
            }
        }

        return j;
    }
}