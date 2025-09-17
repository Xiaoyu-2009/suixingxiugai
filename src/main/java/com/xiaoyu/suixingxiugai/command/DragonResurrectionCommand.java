package com.xiaoyu.suixingxiugai.command;

import java.util.Collection;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.xiaoyu.suixingxiugai.data.PlayerHealthData;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class DragonResurrectionCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("iceandfire")
                .then(Commands.literal("dragonresurrection")
                    .requires(source -> source.hasPermission(0))
                    .then(Commands.argument("targets", EntityArgument.entities())
                        .then(Commands.argument("healthCost", IntegerArgumentType.integer(1))
                            .executes(context -> execute(context, 
                                EntityArgument.getEntities(context, "targets"), 
                                IntegerArgumentType.getInteger(context, "healthCost"))
                            )
                        )
                    )
                )
        );
    }

    private static int execute(CommandContext<CommandSourceStack> context, Collection<? extends Entity> targets, int healthCost) throws CommandSyntaxException {
        Player player = context.getSource().getPlayerOrException();
        int successCount = 0;

        for (Entity targetEntity : targets) {
            if (!(targetEntity instanceof EntityDragonBase dragon)) {
                context.getSource().sendFailure(Component.translatable("commands.iceandfire.dragonresurrection.not_dragon"));
                continue;
            }

            if (!dragon.isModelDead()) {
                String dragonName = getDragonTypeName(dragon);
                context.getSource().sendFailure(Component.translatable("commands.iceandfire.dragonresurrection.not_dead", dragonName));
                continue;
            }

            if (!dragon.isOwnedBy(player)) {
                String dragonName = getDragonTypeName(dragon);
                context.getSource().sendFailure(Component.translatable("commands.iceandfire.dragonresurrection.not_owner", dragonName));
                continue;
            }

            if (player.getMaxHealth() <= healthCost) {
                context.getSource().sendFailure(Component.translatable("commands.iceandfire.dragonresurrection.insufficient_health"));
                continue;
            }

            String dragonName = getDragonTypeName(dragon);

            transferHealthToDragon(player, dragon, healthCost);
            successCount++;

            context.getSource().sendSuccess(() -> Component.translatable("commands.iceandfire.dragonresurrection.success", 
            player.getName().getString(), String.valueOf(healthCost), dragonName), true);
        }

        return successCount;
    }
    
    private static String getDragonTypeName(EntityDragonBase dragon) {
        if (dragon.hasCustomName()) {
            return dragon.getCustomName().getString();
        }

        String entityType = dragon.getType().toString();
        if (entityType.contains("fire_dragon")) {
            return Component.translatable("entity.iceandfire.fire_dragon").getString();
        } else if (entityType.contains("ice_dragon")) {
            return Component.translatable("entity.iceandfire.ice_dragon").getString();
        } else if (entityType.contains("lightning_dragon")) {
            return Component.translatable("entity.iceandfire.lightning_dragon").getString();
        }

        return Component.translatable("entity.iceandfire.dragon_multipart").getString();
    }
    
    private static void transferHealthToDragon(Player player, EntityDragonBase dragon, int healthToTransfer) {
        float newDragonHealth = Math.min(dragon.getMaxHealth(), healthToTransfer);
        dragon.setHealth(newDragonHealth);
        dragon.setModelDead(false);
        reducePlayerMaxHealth(player, healthToTransfer);
    }
    
    private static void reducePlayerMaxHealth(Player player, int healthToReduce) {
        AttributeInstance maxHealthAttribute = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttribute != null) {
            double newBaseValue = maxHealthAttribute.getBaseValue() - healthToReduce;
            maxHealthAttribute.setBaseValue(newBaseValue);

            PlayerHealthData.savePlayerHealthData(player, player.getPersistentData());

            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth((float) player.getMaxHealth());
            }
        }
    }
}