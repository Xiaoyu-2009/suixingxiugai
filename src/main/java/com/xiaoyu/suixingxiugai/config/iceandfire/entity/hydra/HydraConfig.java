package com.xiaoyu.suixingxiugai.config.iceandfire.entity.hydra;

import java.util.List;
import net.minecraftforge.common.ForgeConfigSpec;

public class HydraConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.DoubleValue singleHitDamageToKill;
    public static final ForgeConfigSpec.DoubleValue cumulativeDamageToKill;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> damageTypesToKill;

    static {
        BUILDER.push("IceAndFire (冰火传说)");
        BUILDER.push("Hydra Config (九头蛇配置)");
        
        singleHitDamageToKill = BUILDER
                .comment("九头蛇单次受到多少伤害直接死亡 (How much damage in a single hit kills the hydra)")
                .defineInRange("singleHitDamageToKill", 0.0, 0.0, Double.MAX_VALUE);
                
        cumulativeDamageToKill = BUILDER
                .comment("九头蛇累计受到多少伤害直接死亡 (How much cumulative damage kills the hydra)")
                .defineInRange("cumulativeDamageToKill", 0.0, 0.0, Double.MAX_VALUE);
                
        damageTypesToKill = BUILDER
                .comment("九头蛇受到特定伤害类型直接死亡 (Damage types that kill the hydra instantly)")
                .defineList("damageTypesToKill", 
                        List.of(""),
                        obj -> obj instanceof String);

        BUILDER.pop();
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}