package com.xiaoyu.suixingxiugai.entity.ai.goal;

import java.util.EnumSet;

import com.xiaoyu.suixingxiugai.util.NagaPhysicsUtil;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import twilightforest.entity.EnforcedHomePoint;
import twilightforest.entity.boss.Naga;

public class TeleportToHomeGoal<T extends PathfinderMob & EnforcedHomePoint> extends Goal {
    private final T mob;
    private final double speedModifier;

    public TeleportToHomeGoal(T mob, double speedModifier) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.mob instanceof Naga naga) {
            return this.mob.getRestrictionPoint() != null && !NagaPhysicsUtil.isEntityWithinNagaCourtyard(naga, naga);
        }
        return this.mob.getRestrictionPoint() != null && !this.mob.isMobWithinHomeArea(this.mob);
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        if (this.mob.getRestrictionPoint() != null) {
            Vec3 homePos = Vec3.atBottomCenterOf(this.mob.getRestrictionPoint().pos());
            this.mob.teleportTo(homePos.x, homePos.y, homePos.z);
        }
    }
}