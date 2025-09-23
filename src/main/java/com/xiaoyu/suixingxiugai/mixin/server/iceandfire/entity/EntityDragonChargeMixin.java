package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.entity;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.entity.EntityDragonCharge;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.UUID;

@Mixin(EntityDragonCharge.class)
public abstract class EntityDragonChargeMixin {

    @Nullable
    private UUID ownerUUID;

    @Inject(
        method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", 
        at = @At("TAIL")
    )
    private void onInit(EntityType<? extends EntityDragonCharge> type, Level worldIn, CallbackInfo ci) {
        setOwnerUUIDFromShooter();
    }

    @Inject(
        method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;Lcom/github/alexthe666/iceandfire/entity/EntityDragonBase;DDD)V", 
        at = @At("TAIL")
    )
    private void onInitWithShooter(EntityType<? extends EntityDragonCharge> type, Level worldIn, com.github.alexthe666.iceandfire.entity.EntityDragonBase shooter, double accelX, double accelY, double accelZ, CallbackInfo ci) {
        setOwnerUUIDFromShooter();
    }

    private void setOwnerUUIDFromShooter() {
        EntityDragonCharge charge = (EntityDragonCharge) (Object) this;
        if (charge.getOwner() instanceof EntityDragonBase) {
            EntityDragonBase dragon = (EntityDragonBase) charge.getOwner();
            if (dragon.isTame() && dragon.getOwner() instanceof Player) {
                Player player = (Player) dragon.getOwner();
                this.ownerUUID = player.getUUID();
            }
        }
    }

    @Nullable
    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public void setOwnerUUID(@Nullable UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }
}