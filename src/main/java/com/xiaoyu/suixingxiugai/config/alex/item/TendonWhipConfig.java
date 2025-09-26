package com.xiaoyu.suixingxiugai.config.alex.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class TendonWhipConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue whipAttackRange;
    public static final ForgeConfigSpec.IntValue whipMaxTargets;
    public static final ForgeConfigSpec.DoubleValue whipMaxDamage;
    public static final ForgeConfigSpec.BooleanValue whipRequireFullCharge;
    public static final ForgeConfigSpec.BooleanValue whipQuickRetract;

    static {
        BUILDER.push("Tendon Whip Config (筋腱长鞭配置)");

        whipAttackRange = BUILDER
                .comment("筋腱长鞭攻击的距离 (Tendon Whip attack range)")
                .defineInRange("whipAttackRange", 5.0, 1.0, Double.MAX_VALUE);

        whipMaxTargets = BUILDER
                .comment("筋腱长鞭最多同时攻击生物数量 (Maximum number of entities that Tendon Whip can attack simultaneously)")
                .defineInRange("whipMaxTargets", 3, 1, Integer.MAX_VALUE);

        whipMaxDamage = BUILDER
                .comment("筋腱长鞭单次攻击的最大伤害 (Maximum damage per Tendon Whip attack)")
                .defineInRange("whipMaxDamage", 5.0, 0.0, Double.MAX_VALUE);
                
        whipRequireFullCharge = BUILDER
                .comment("筋腱长鞭是否需要蓄力满才能攻击 (Whether Tendon Whip requires full charge to attack)")
                .define("whipRequireFullCharge", false);
                
        whipQuickRetract = BUILDER
                .comment("筋腱长鞭是否开启右键快速收回 (Whether Tendon Whip quick retract with right click is enabled)")
                .define("whipQuickRetract", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}