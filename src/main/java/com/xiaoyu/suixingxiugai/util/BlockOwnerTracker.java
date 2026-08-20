package com.xiaoyu.suixingxiugai.util;

import com.xiaoyu.suixingxiugai.gamerule.SuixingxiugaiGameRules;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class BlockOwnerTracker {
    private static final Map<BlockPos, UUID> blockOwners = new ConcurrentHashMap<>();
    private static final Map<Level, Map<BlockPos, UUID>> worldBlockOwners = new ConcurrentHashMap<>();

    public static void setBlockOwner(BlockPos pos, UUID ownerUUID) {
        blockOwners.put(pos, ownerUUID);
    }

    @Nullable
    public static UUID getBlockOwner(BlockPos pos) {
        return blockOwners.get(pos);
    }

    public static boolean isBlockOwner(BlockPos pos, Player player) {
        if (!player.level().getGameRules().getBoolean(SuixingxiugaiGameRules.ICE_AND_FIRE_DRAGON_PVP)) {
            UUID ownerUUID = getBlockOwner(pos);
            return ownerUUID != null && ownerUUID.equals(player.getUUID());
        }
        return false;
    }

    public static void removeBlockOwner(BlockPos pos) {
        blockOwners.remove(pos);
    }

    public static void clearWorldBlockOwners(Level level) {
        worldBlockOwners.remove(level);
    }
}