package com.xiaoyu.suixingxiugai.config.alex.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class KomodoDragonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue komodoDragonWillAttack;

    static {
        BUILDER.push("Komodo Dragon Config (科莫多巨蜥配置)");

        komodoDragonWillAttack = BUILDER
                .comment("科莫多巨蜥是否会主动攻击 (Whether Komodo Dragons will actively attack)")
                .define("komodoDragonWillAttack", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}