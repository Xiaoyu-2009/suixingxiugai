package com.xiaoyu.suixingxiugai.compat.cloth;

import com.xiaoyu.suixingxiugai.compat.cloth.alex.AlexConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.bosses_of_mass_destruction.BossesOfMassDestructionConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.iceandfire.IceAndFireConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.TwilightForestConfigScreen;
import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.xiaoyu.suixingxiugai.config.alex.entity.KomodoDragonConfig;
import com.xiaoyu.suixingxiugai.config.alex.item.TendonWhipConfig;
import com.xiaoyu.suixingxiugai.config.bosses_of_mass_destruction.entity.GauntletConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.GazeImmunityConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.IceandfireConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DragonConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.DreadMobConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.entity.HydraConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.CyclopsEyeConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.DeathwormGauntletConfig;
import com.xiaoyu.suixingxiugai.config.iceandfire.item.HydraHeartConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.AlphaYetiConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.MinoshroomConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.NagaConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;
import com.xiaoyu.suixingxiugai.util.ConfigEntryHelper;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SuixingxiugaiConfigScreen {

    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("config.suixingxiugai.title"));

        builder.setSavingRunnable(() -> {
            SuixingxiugaiConfig.SPEC.save();
            LichConfig.SPEC.save();
            NagaConfig.SPEC.save();
            MinoshroomConfig.SPEC.save();
            WandConfig.SPEC.save();
            IceandfireConfig.SPEC.save();
            GazeImmunityConfig.SPEC.save();
            DragonConfig.SPEC.save();
            DreadMobConfig.SPEC.save();
            HydraConfig.SPEC.save();
            DeathwormGauntletConfig.SPEC.save();
            CyclopsEyeConfig.SPEC.save();
            HydraHeartConfig.SPEC.save();
            /* CyclopsEyeCuriosConfig.SPEC.save();
            HydraHeartCuriosConfig.SPEC.save(); */
            KomodoDragonConfig.SPEC.save();
            TendonWhipConfig.SPEC.save();
            GauntletConfig.SPEC.save();
            AlphaYetiConfig.SPEC.save();
        });

        createMainConfigScreen(builder);
        TwilightForestConfigScreen.createTwilightForestConfigScreen(builder);
        IceAndFireConfigScreen.createIceAndFireConfigScreen(builder);
        /* CuriosConfigScreen.createCuriosConfigScreen(builder); */
        AlexConfigScreen.createAlexConfigScreen(builder);
        BossesOfMassDestructionConfigScreen.createBossesOfMassDestructionConfigScreen(builder);

        return builder.build();
    }

    private static void createMainConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory generalCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.general"));

        generalCategory.addEntry(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.disable_experimental_warning"),
                SuixingxiugaiConfig.disableExperimentalWarning,
                true));

        generalCategory.addEntry(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.enable_damage_randomization"),
                SuixingxiugaiConfig.enableDamageRandomization,
                false));

        generalCategory.addEntry(ConfigEntryHelper.createStringDropdownMenu(
                entryBuilder,
                Component.translatable("config.suixingxiugai.damage_randomization_entity_types"),
                SuixingxiugaiConfig.damageRandomizationEntityTypes,
                "living",
                List.of("mob", "living", "player", "all", "animal", "monster", "ambient", "water_animal", "flying")));

        generalCategory.addEntry(ConfigEntryHelper.createStringList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.damage_randomization_entities"),
                SuixingxiugaiConfig.damageRandomizationEntities,
                List.of()));

        generalCategory.addEntry(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.damage_randomization_min_multiplier"),
                SuixingxiugaiConfig.damageRandomizationMinMultiplier,
                0.5,
                0.0,
                Double.MAX_VALUE));

        generalCategory.addEntry(ConfigEntryHelper.createDoubleField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.damage_randomization_max_multiplier"),
                SuixingxiugaiConfig.damageRandomizationMaxMultiplier,
                1.5,
                0.0,
                Double.MAX_VALUE));

        generalCategory.addEntry(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.enable_damage_number_display"),
                SuixingxiugaiConfig.enableDamageNumberDisplay,
                false));

        generalCategory.addEntry(ConfigEntryHelper.createStringDropdownMenu(
                entryBuilder,
                Component.translatable("config.suixingxiugai.damage_number_display_entity_types"),
                SuixingxiugaiConfig.damageNumberDisplayEntityTypes,
                "living",
                List.of("mob", "living", "player", "all", "animal", "monster", "ambient", "water_animal", "flying")));

        generalCategory.addEntry(ConfigEntryHelper.createStringList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.damage_number_display_entities"),
                SuixingxiugaiConfig.damageNumberDisplayEntities,
                List.of()));

        generalCategory.addEntry(ConfigEntryHelper.createStringList(
                entryBuilder,
                Component.translatable("config.suixingxiugai.damage_number_display_blacklist_entities"),
                SuixingxiugaiConfig.damageNumberDisplayBlacklistEntities,
                List.of("dummmmmmy:target_dummy")));

        generalCategory.addEntry(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.damage_number_color"),
                SuixingxiugaiConfig.damageNumberColor,
                "#FFFFFF"));
                
        generalCategory.addEntry(ConfigEntryHelper.createStringField(
                entryBuilder,
                Component.translatable("config.suixingxiugai.crit_damage_number_color"),
                SuixingxiugaiConfig.critDamageNumberColor,
                "#FF0000"));
                
        generalCategory.addEntry(ConfigEntryHelper.createBooleanToggle(
                entryBuilder,
                Component.translatable("config.suixingxiugai.enable_keep_inventory_on_enter_world"),
                SuixingxiugaiConfig.enableKeepInventoryOnEnterWorld,
                false));
    }
}