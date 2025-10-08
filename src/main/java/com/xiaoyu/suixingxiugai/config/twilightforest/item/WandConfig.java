package com.xiaoyu.suixingxiugai.config.twilightforest.item;

import net.minecraftforge.common.ForgeConfigSpec;

public class WandConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    
    public static final ForgeConfigSpec.BooleanValue enableFortificationWandTargeting;
    public static final ForgeConfigSpec.IntValue fortificationWandShieldAmount;
    public static final ForgeConfigSpec.BooleanValue enableFortificationWandInvulnerability;
    public static final ForgeConfigSpec.IntValue fortificationWandUses;
    public static final ForgeConfigSpec.IntValue zombieWandUses;
    public static final ForgeConfigSpec.IntValue twilightWandUses;
    public static final ForgeConfigSpec.IntValue lifedrainScepterUses;
    public static final ForgeConfigSpec.IntValue fortificationWandCooldown;
    public static final ForgeConfigSpec.IntValue zombieWandCooldown;
    public static final ForgeConfigSpec.IntValue twilightWandCooldown;
    public static final ForgeConfigSpec.IntValue lifedrainScepterCooldown;

    static {
        BUILDER.push("Wand Config (权杖配置)");
        
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
                
        zombieWandUses = BUILDER
                .comment("僵尸权杖使用次数 (Number of uses for zombie wand)")
                .defineInRange("zombieWandUses", 9, 1, Integer.MAX_VALUE);
                
        twilightWandUses = BUILDER
                .comment("黄昏权杖使用次数 (Number of uses for twilight wand)")
                .defineInRange("twilightWandUses", 99, 1, Integer.MAX_VALUE);
                
        lifedrainScepterUses = BUILDER
                .comment("吸血权杖使用次数 (Number of uses for lifedrain scepter)")
                .defineInRange("lifedrainScepterUses", 99, 1, Integer.MAX_VALUE);

        fortificationWandCooldown = BUILDER
                .comment("护盾权杖冷却时间 (Cooldown time for fortification wand in ticks)")
                .defineInRange("fortificationWandCooldown", 1200, 0, Integer.MAX_VALUE);
                
        zombieWandCooldown = BUILDER
                .comment("僵尸权杖冷却时间 (Cooldown time for zombie wand in ticks)")
                .defineInRange("zombieWandCooldown", 0, 0, Integer.MAX_VALUE);
                
        twilightWandCooldown = BUILDER
                .comment("黄昏权杖冷却时间 (Cooldown time for twilight wand in ticks)")
                .defineInRange("twilightWandCooldown", 0, 0, Integer.MAX_VALUE);
                
        lifedrainScepterCooldown = BUILDER
                .comment("吸血权杖冷却时间 (Cooldown time for lifedrain scepter in ticks)")
                .defineInRange("lifedrainScepterCooldown", 0, 0, Integer.MAX_VALUE);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}