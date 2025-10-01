package com.xiaoyu.suixingxiugai.compat.cloth;

import com.xiaoyu.suixingxiugai.compat.cloth.alex.AlexConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.bosses_of_mass_destruction.BossesOfMassDestructionConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.curios.CuriosConfigScreen;
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
import com.xiaoyu.suixingxiugai.config.iceandfire.item.DeathwormGauntletConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.LichConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.entity.NagaConfig;
import com.xiaoyu.suixingxiugai.config.twilightforest.item.WandConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
            WandConfig.SPEC.save();
            IceandfireConfig.SPEC.save();
            GazeImmunityConfig.SPEC.save();
            DragonConfig.SPEC.save();
            DreadMobConfig.SPEC.save();
            HydraConfig.SPEC.save();
            DeathwormGauntletConfig.SPEC.save();
            com.xiaoyu.suixingxiugai.config.iceandfire.item.CyclopsEyeConfig.SPEC.save();
            com.xiaoyu.suixingxiugai.config.iceandfire.item.HydraHeartConfig.SPEC.save();
            com.xiaoyu.suixingxiugai.config.curios.iceandfire.CyclopsEyeConfig.SPEC.save();
            com.xiaoyu.suixingxiugai.config.curios.iceandfire.HydraHeartConfig.SPEC.save();
            KomodoDragonConfig.SPEC.save();
            TendonWhipConfig.SPEC.save();
            GauntletConfig.SPEC.save();
        });

        createMainConfigScreen(builder);
        TwilightForestConfigScreen.createTwilightForestConfigScreen(builder);
        IceAndFireConfigScreen.createIceAndFireConfigScreen(builder);
        CuriosConfigScreen.createCuriosConfigScreen(builder);
        AlexConfigScreen.createAlexConfigScreen(builder);
        BossesOfMassDestructionConfigScreen.createBossesOfMassDestructionConfigScreen(builder);

        return builder.build();
    }

    private static void createMainConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory generalCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.general"));

        generalCategory.addEntry(entryBuilder
                .startBooleanToggle(Component.translatable("config.suixingxiugai.disable_experimental_warning"), SuixingxiugaiConfig.disableExperimentalWarning.get())
                .setDefaultValue(true)
                .setSaveConsumer(value -> SuixingxiugaiConfig.disableExperimentalWarning.set(value))
                .build());
    }
}