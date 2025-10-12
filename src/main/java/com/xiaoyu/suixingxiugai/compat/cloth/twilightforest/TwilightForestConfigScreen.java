package com.xiaoyu.suixingxiugai.compat.cloth.twilightforest;

import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity.AlphaYetiConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity.KnightPhantomConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity.LichConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity.MinoshroomConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.entity.NagaConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item.wand.FortificationWandConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item.wand.ZombieWandConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item.wand.TwilightWandConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.twilightforest.item.wand.LifedrainScepterConfigScreen;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TwilightForestConfigScreen {
    
    public static void createTwilightForestConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory twilightForestCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.twilightforest"));

        SubCategoryBuilder entitySubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.entity"));
        entitySubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder lichSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.lich"));
        lichSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> lichEntries = LichConfigScreen.createLichConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : lichEntries) {
            lichSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(lichSubCategoryBuilder.build());

        SubCategoryBuilder nagaSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.naga"));
        nagaSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> nagaEntries = NagaConfigScreen.createNagaConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : nagaEntries) {
            nagaSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(nagaSubCategoryBuilder.build());
        
        SubCategoryBuilder minoshroomSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.minoshroom"));
        minoshroomSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> minoshroomEntries = MinoshroomConfigScreen.createMinoshroomConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : minoshroomEntries) {
            minoshroomSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(minoshroomSubCategoryBuilder.build());

        SubCategoryBuilder alphaYetiSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.alpha_yeti"));
        alphaYetiSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> alphaYetiEntries = AlphaYetiConfigScreen.createAlphaYetiConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : alphaYetiEntries) {
            alphaYetiSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(alphaYetiSubCategoryBuilder.build());
        
        SubCategoryBuilder knightPhantomSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.knight_phantom"));
        knightPhantomSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> knightPhantomEntries = KnightPhantomConfigScreen.createKnightPhantomConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : knightPhantomEntries) {
            knightPhantomSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(knightPhantomSubCategoryBuilder.build());
        
        twilightForestCategory.addEntry(entitySubCategoryBuilder.build());

        SubCategoryBuilder itemSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.item"));
        itemSubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder fortificationWandSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.wand.fortification"));
        fortificationWandSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> fortificationWandEntries = FortificationWandConfigScreen.createFortificationWandConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : fortificationWandEntries) {
            fortificationWandSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(fortificationWandSubCategoryBuilder.build());

        SubCategoryBuilder zombieWandSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.wand.zombie"));
        zombieWandSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> zombieWandEntries = ZombieWandConfigScreen.createZombieWandConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : zombieWandEntries) {
            zombieWandSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(zombieWandSubCategoryBuilder.build());

        SubCategoryBuilder twilightWandSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.wand.twilight"));
        twilightWandSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> twilightWandEntries = TwilightWandConfigScreen.createTwilightWandConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : twilightWandEntries) {
            twilightWandSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(twilightWandSubCategoryBuilder.build());

        SubCategoryBuilder lifedrainScepterSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.twilightforest.wand.lifedrain"));
        lifedrainScepterSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> lifedrainScepterEntries = LifedrainScepterConfigScreen.createLifedrainScepterConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : lifedrainScepterEntries) {
            lifedrainScepterSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(lifedrainScepterSubCategoryBuilder.build());
        
        twilightForestCategory.addEntry(itemSubCategoryBuilder.build());
    }
}