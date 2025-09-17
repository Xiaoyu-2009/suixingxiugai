package com.xiaoyu.suixingxiugai.mixin.server.twilightforest.capabilities.shield;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import twilightforest.capabilities.shield.IShieldCapability;
import twilightforest.network.TFPacketHandler;
import twilightforest.network.UpdateShieldPacket;

@Mixin(targets = "twilightforest.capabilities.shield.ShieldCapabilityHandler", remap = false)
public class ShieldCapabilityHandlerMixin {
    
    @Shadow
    private LivingEntity host;

    @Inject(method = "sendUpdatePacket", at = @At("HEAD"), cancellable = true)
    private void onSendUpdatePacket(CallbackInfo ci) {
        ci.cancel();

        if (this.host != null) {
            IShieldCapability cap = (IShieldCapability) (Object) this;
            if (this.host instanceof ServerPlayer) {
                TFPacketHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this.host), new UpdateShieldPacket(this.host, cap));
            } else {
                TFPacketHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.host), new UpdateShieldPacket(this.host, cap));
            }
        }
    }
}