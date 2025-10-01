package com.xiaoyu.suixingxiugai.config.bosses_of_mass_destruction.entity;

import net.minecraftforge.common.ForgeConfigSpec;

public class GauntletConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.BooleanValue gauntletDefenseCanBeAttacked;
    public static final ForgeConfigSpec.BooleanValue gauntletIdleHealEnabled;

    static {
        BUILDER.push("Gauntlet Config (下界铁掌配置)");

        gauntletDefenseCanBeAttacked = BUILDER
                .comment("下界铁掌防御状态下是否可以被攻击 (Whether the Gauntlet can be attacked when in defensive state)")
                .define("gauntletDefenseCanBeAttacked", false);
        
        gauntletIdleHealEnabled = BUILDER
                .comment("下界铁掌脱战后是否会自行恢复生命值 (Whether the Gauntlet will heal itself after leaving combat)")
                .define("gauntletIdleHealEnabled", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}