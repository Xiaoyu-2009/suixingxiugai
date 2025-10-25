package com.xiaoyu.suixingxiugai.command;

import com.github.alexthe666.iceandfire.entity.EntityStoneStatue;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;

public class StatueRestoreCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("iceandfire")
                .then(Commands.literal("statuerestore")
                    .requires(source -> source.hasPermission(2))
                    .then(Commands.argument("targets", EntityArgument.entities())
                        .executes(context -> execute(context, 
                            EntityArgument.getEntities(context, "targets"))
                        )
                    )
                )
        );
    }

    private static int execute(CommandContext<CommandSourceStack> context, Collection<? extends Entity> targets) throws CommandSyntaxException {
        int successCount = 0;

        for (Entity targetEntity : targets) {
            if (!(targetEntity instanceof EntityStoneStatue statue)) {
                context.getSource().sendFailure(Component.translatable("commands.iceandfire.statuerestore.not_statue"));
                continue;
            }

            String entityTypeString = statue.getTrappedEntityTypeString();
            CompoundTag entityData = statue.getTrappedTag();

            EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.tryParse(entityTypeString));
            if (entityType == null) {
                context.getSource().sendFailure(Component.translatable("commands.iceandfire.statuerestore.invalid_entity_type"));
                continue;
            }

            ServerLevel level = (ServerLevel) statue.level();
            Entity originalEntity = entityType.create(level);
            if (originalEntity == null) {
                context.getSource().sendFailure(Component.translatable("commands.iceandfire.statuerestore.restore_failed", statue.getDisplayName().getString()));
                continue;
            }

            try {
                originalEntity.load(entityData);
            } catch (Exception e) {
                context.getSource().sendFailure(Component.translatable("commands.iceandfire.statuerestore.restore_failed", statue.getDisplayName().getString()));
                continue;
            }

            originalEntity.absMoveTo(statue.getX(), statue.getY(), statue.getZ(), statue.getYRot(), statue.getXRot());
            if (originalEntity instanceof LivingEntity living) {
                living.yBodyRot = statue.yBodyRot;
                living.yHeadRot = statue.yHeadRot;
            }

            statue.remove(Entity.RemovalReason.DISCARDED);
            level.addFreshEntity(originalEntity);

            successCount++;
            
            String entityName = originalEntity.getName().getString();
            context.getSource().sendSuccess(() -> Component.translatable("commands.iceandfire.statuerestore.success", entityName), true);
        }

        return successCount;
    }
}