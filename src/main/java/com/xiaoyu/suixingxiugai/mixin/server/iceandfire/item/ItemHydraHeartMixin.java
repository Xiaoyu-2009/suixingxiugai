package com.xiaoyu.suixingxiugai.mixin.server.iceandfire.item;

import com.github.alexthe666.iceandfire.item.ItemHydraHeart;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.HydraHeartConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemHydraHeart.class)
public class ItemHydraHeartMixin {
    
    @Inject(
        method = "inventoryTick",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onInventoryTickHead(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected, CallbackInfo ci) {
        if (entity instanceof Player player && itemSlot >= 0 && itemSlot <= 8) {
            applyHydraHeartEffect(player, stack);
            ci.cancel();
            return;
        }

        /*
        if (HydraHeartCuriosConfig.hydraHeartWorkAsCurio.get()) {
            if (entity instanceof Player player && isWornAsCurio(player, stack)) {
                applyHydraHeartEffect(player, stack);
                ci.cancel();
            }
        }
        */
    }

    /*
    private boolean isWornAsCurio(Player player, ItemStack stack) {
        return CuriosApi.getCuriosInventory(player)
        .resolve()
        .flatMap(inv -> inv.findFirstCurio(itemStack -> itemStack == stack))
        .isPresent();
    }
    */
    
    private void applyHydraHeartEffect(Player player, ItemStack stack) {
        double healthPercentage = player.getHealth() / Math.max(1, player.getMaxHealth());
        if (healthPercentage < 1.0D) {
            int potionLevel = getPotionLevelFromConfig(healthPercentage);

            if (HydraHeartConfig.hydraHeartStackPotionLevels.get()) {
                potionLevel = getTotalPotionLevel(player, healthPercentage);
            }
            
            int potionDuration = HydraHeartConfig.hydraHeartPotionDuration.get();
            String potionEffectId = HydraHeartConfig.hydraHeartPotionEffect.get();
            MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(ResourceLocation.tryParse(potionEffectId));
            
            if (effect != null && potionLevel >= 0) {
                if (HydraHeartConfig.hydraHeartApplyEffectIfAlreadyHas.get() || !player.hasEffect(effect)) {
                    player.addEffect(new MobEffectInstance(effect, potionDuration, potionLevel, true, false));
                }
            }
        }
    }
    
    private int getTotalPotionLevel(Player player, double healthPercentage) {
        int totalLevel = 0;

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (stack.getItem() instanceof ItemHydraHeart) {
                totalLevel += getPotionLevelFromConfig(healthPercentage);
            }
        }

        /*
        if (HydraHeartCuriosConfig.hydraHeartWorkAsCurio.get()) {
            totalLevel += getCurioPotionLevels(player, healthPercentage);
        }
        */
        
        return totalLevel;
    }

    /*
    private int getCurioPotionLevels(Player player, double healthPercentage) {
        int levels = 0;
        var curiosInv = CuriosApi.getCuriosInventory(player).resolve();
        if (curiosInv.isPresent()) {
            var inv = curiosInv.get();
            for (var entry : inv.getCurios().entrySet()) {
                var curio = entry.getValue();
                for (int i = 0; i < curio.getStacks().getSlots(); i++) {
                    ItemStack stack = curio.getStacks().getStackInSlot(i);
                    if (stack.getItem() instanceof ItemHydraHeart) {
                        levels += getPotionLevelFromConfig(healthPercentage);
                    }
                }
            }
        }
        return levels;
    }
    */
    
    private int getPotionLevelFromConfig(double healthPercentage) {
        List<? extends Double> thresholds = HydraHeartConfig.hydraHeartPotionThresholds.get();
        List<? extends Integer> levels = HydraHeartConfig.hydraHeartPotionLevels.get();

        if (thresholds.size() + 1 != levels.size()) {
            return 0;
        }

        for (int i = 0; i < thresholds.size(); i++) {
            if (healthPercentage > thresholds.get(i)) {
                return levels.get(i);
            }
        }

        return levels.get(levels.size() - 1);
    }
}