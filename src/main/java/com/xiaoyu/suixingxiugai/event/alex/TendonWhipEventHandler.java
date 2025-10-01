package com.xiaoyu.suixingxiugai.event.alex;

import com.xiaoyu.suixingxiugai.SuixingXiugai;
import com.xiaoyu.suixingxiugai.config.alex.item.TendonWhipConfig;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Method;

@Mod.EventBusSubscriber(modid = SuixingXiugai.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TendonWhipEventHandler {

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (!TendonWhipConfig.whipQuickRetract.get()) {
            return;
        }

        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack stack = player.getItemInHand(hand);

        if (isTendonWhip(stack)) {
            retractTendonWhip(player);
        }
    }

    private static boolean isTendonWhip(ItemStack stack) {
        if (stack.isEmpty()) return false;
        
        try {
            Class<?> amItemRegistryClass = Class.forName("com.github.alexthe666.alexsmobs.item.AMItemRegistry");
            Object tendonWhipObject = amItemRegistryClass.getField("TENDON_WHIP").get(null);

            Method getMethod = tendonWhipObject.getClass().getMethod("get");
            Object tendonWhipItem = getMethod.invoke(tendonWhipObject);
            
            return stack.getItem() == tendonWhipItem;
        } catch (Exception e) {
            return false;
        }
    }

    private static void retractTendonWhip(Object player) {
        try {
            Class<?> tendonWhipUtilClass = Class.forName("com.github.alexthe666.alexsmobs.entity.util.TendonWhipUtil");
            Class<?> livingEntityClass = Class.forName("net.minecraft.world.entity.LivingEntity");
            Class<?> levelClass = Class.forName("net.minecraft.world.level.Level");

            Method getLastTendonMethod = tendonWhipUtilClass.getMethod("getLastTendon", livingEntityClass);
            Method getLevelMethod = livingEntityClass.getMethod("level");

            Object level = getLevelMethod.invoke(player);
            Object lastTendon = getLastTendonMethod.invoke(null, player);
            
            if (lastTendon != null) {
                setTendonChainRetracting(lastTendon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void setTendonChainRetracting(Object tendonSegment) {
        try {
            Class<?> tendonSegmentClass = Class.forName("com.github.alexthe666.alexsmobs.entity.EntityTendonSegment");

            Method setRetractingMethod = tendonSegmentClass.getMethod("setRetracting", boolean.class);
            setRetractingMethod.invoke(tendonSegment, true);

            Method getFromEntityMethod = tendonSegmentClass.getMethod("getFromEntity");
            Object fromEntity = getFromEntityMethod.invoke(tendonSegment);

            if (fromEntity != null && fromEntity.getClass() == tendonSegmentClass) {
                setTendonChainRetracting(fromEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}