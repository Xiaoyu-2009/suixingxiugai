package com.xiaoyu.suixingxiugai.config.twilightforest.item.wand;

import net.minecraftforge.common.ForgeConfigSpec;

public class FortificationWandConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue enableFortificationWandTargeting;
    public static final ForgeConfigSpec.IntValue fortificationWandShieldAmount;
    public static final ForgeConfigSpec.BooleanValue enableFortificationWandInvulnerability;
    public static final ForgeConfigSpec.IntValue fortificationWandUses;
    public static final ForgeConfigSpec.IntValue fortificationWandCooldown;

    static {
        BUILDER.push("Fortification Wand Config (护盾权杖配置)");
        
        enableFortificationWandTargeting = BUILDER
                .comment("护盾权杖目标选择功能 (Enable target selection for fortification wand)")
                .define("enableFortificationWandTargeting", false);
                
        fortificationWandShieldAmount = BUILDER
                .comment("护盾权杖单次施加的护盾数量 (Number of shields granted by fortification wand per use)")
                .defineInRange("fortificationWandShieldAmount", 5, 1, Integer.MAX_VALUE);

        enableFortificationWandInvulnerability = BUILDER
                .comment("护盾权杖施加的护盾免疫一切伤害 (Entities with fortification wand shields are invulnerable to all damage)")
                .define("enableFortificationWandInvulnerability", false);

        fortificationWandUses = BUILDER
                .comment("护盾权杖使用次数 (Number of uses for fortification wand)")
                .defineInRange("fortificationWandUses", 9, 1, Integer.MAX_VALUE);
                
        fortificationWandCooldown = BUILDER
                .comment("护盾权杖冷却时间 (Cooldown time for fortification wand in ticks)")
                .defineInRange("fortificationWandCooldown", 1200, 0, Integer.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}