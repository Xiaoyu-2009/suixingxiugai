package com.xiaoyu.suixingxiugai.compat.cloth.alex;

import com.xiaoyu.suixingxiugai.compat.cloth.alex.entity.KomodoDragonConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.alex.item.BloodSprayerConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.alex.item.HemolymphBlasterConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.alex.item.PocketSandConfigScreen;
import com.xiaoyu.suixingxiugai.compat.cloth.alex.item.TendonWhipConfigScreen;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;

import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AlexConfigScreen {
    
    public static void createAlexConfigScreen(ConfigBuilder builder) {
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory alexCategory = builder.getOrCreateCategory(Component.translatable("config.suixingxiugai.alex"));

        SubCategoryBuilder entitySubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.alex.entity"));
        entitySubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder komodoDragonSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.alex.entity.komodo_dragon"));
        komodoDragonSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> komodoDragonEntries = KomodoDragonConfigScreen.createKomodoDragonConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : komodoDragonEntries) {
            komodoDragonSubCategoryBuilder.add(entry);
        }
        entitySubCategoryBuilder.add(komodoDragonSubCategoryBuilder.build());
        alexCategory.addEntry(entitySubCategoryBuilder.build());

        SubCategoryBuilder itemSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.alex.item"));
        itemSubCategoryBuilder.setExpanded(false);

        SubCategoryBuilder bloodSprayerSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.alex.item.blood_sprayer"));
        bloodSprayerSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> bloodSprayerEntries = BloodSprayerConfigScreen.createBloodSprayerConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : bloodSprayerEntries) {
            bloodSprayerSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(bloodSprayerSubCategoryBuilder.build());
        
        SubCategoryBuilder hemolymphBlasterSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.alex.item.hemolymph_blaster"));
        hemolymphBlasterSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> hemolymphBlasterEntries = HemolymphBlasterConfigScreen.createHemolymphBlasterConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : hemolymphBlasterEntries) {
            hemolymphBlasterSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(hemolymphBlasterSubCategoryBuilder.build());
        
        SubCategoryBuilder pocketSandSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.alex.item.pocket_sand"));
        pocketSandSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> pocketSandEntries = PocketSandConfigScreen.createPocketSandConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : pocketSandEntries) {
            pocketSandSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(pocketSandSubCategoryBuilder.build());
        
        SubCategoryBuilder tendonWhipSubCategoryBuilder = entryBuilder.startSubCategory(Component.translatable("config.suixingxiugai.alex.item.tendon_whip"));
        tendonWhipSubCategoryBuilder.setExpanded(false);
        List<AbstractConfigListEntry> tendonWhipEntries = TendonWhipConfigScreen.createTendonWhipConfigEntries(entryBuilder);
        for (AbstractConfigListEntry entry : tendonWhipEntries) {
            tendonWhipSubCategoryBuilder.add(entry);
        }
        itemSubCategoryBuilder.add(tendonWhipSubCategoryBuilder.build());
        alexCategory.addEntry(itemSubCategoryBuilder.build());
    }
}