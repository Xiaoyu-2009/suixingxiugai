package com.xiaoyu.suixingxiugai.mixin.server.iceandfire;

import com.github.alexthe666.iceandfire.block.BlockIceSpikes;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.IafDragonDestructionManager;
import com.xiaoyu.suixingxiugai.util.BlockOwnerTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.annotation.Nullable;
import java.util.UUID;

@Mixin(IafDragonDestructionManager.class)
public class IafDragonDestructionManagerMixin {

    @Inject(
        method = "attackBlock(Lnet/minecraft/world/level/Level;Lcom/github/alexthe666/iceandfire/entity/EntityDragonBase;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V",
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z", 
            shift = At.Shift.AFTER, ordinal = 1
        ), 
        locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private static void onSetBlockAndUpdate(Level level, EntityDragonBase dragon, BlockPos position, BlockState state, 
    CallbackInfo ci, BlockState transformed, net.minecraft.world.level.block.Block elementalBlock, 
    boolean doPlaceBlock, BlockState stateAbove) {
        if (elementalBlock == Blocks.FIRE || elementalBlock == IafBlockRegistry.DRAGON_ICE_SPIKES.get()) {
            UUID ownerUUID = getDragonOwnerUUID(dragon);
            if (ownerUUID != null) {
                markBlockWithOwner(level, position.above(), ownerUUID);
            }
        }
    }

    @Nullable
    private static UUID getDragonOwnerUUID(EntityDragonBase dragon) {
        if (dragon.isTame() && dragon.getOwner() != null) {
            return dragon.getOwner().getUUID();
        }
        return null;
    }

    private static void markBlockWithOwner(Level level, BlockPos pos, UUID ownerUUID) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof BlockIceSpikes || state.getBlock() == Blocks.FIRE) {
            CompoundTag blockEntityTag = new CompoundTag();
            blockEntityTag.putUUID("DragonOwner", ownerUUID);
            BlockOwnerTracker.setBlockOwner(pos, ownerUUID);
        }
    }
}