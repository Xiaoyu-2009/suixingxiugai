package com.xiaoyu.suixingxiugai.client;

import com.xiaoyu.suixingxiugai.config.SuixingxiugaiConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DamageNumberParticle extends Particle {

    private final Font fontRenderer = Minecraft.getInstance().font;
    private final Component text;
    private final int color;
    private final int darkColor;
    private final boolean isCrit;
    private float fadeout = -1;
    private float prevFadeout = -1;
    private float visualDY = 0;
    private float prevVisualDY = 0;
    private float visualDX = 0;
    private float prevVisualDX = 0;

    public DamageNumberParticle(ClientLevel clientLevel, double x, double y, double z, double damageAmount, double critData) {
        super(clientLevel, x, y, z);
        this.lifetime = 35;

        this.isCrit = critData > 0;

        if (this.isCrit) {
            String critColorString = SuixingxiugaiConfig.critDamageNumberColor.get();
            this.color = parseColor(critColorString);
        } else {
            String colorString = SuixingxiugaiConfig.damageNumberColor.get();
            this.color = parseColor(colorString);
        }

        int red = (this.color >> 16) & 0xFF;
        int green = (this.color >> 8) & 0xFF;
        int blue = this.color & 0xFF;
        this.darkColor = (0xFF << 24) | ((int)(red * 0.25f) << 16) | ((int)(green * 0.25f) << 8) | (int)(blue * 0.25f);

        this.text = Component.literal(String.format("%.1f", Math.abs(damageAmount)));
        this.yd = 1;

        if (this.isCrit) {
            this.xd = (clientLevel.random.nextFloat() - 0.5) * 1.0;
        } else {
            this.xd = (clientLevel.random.nextFloat() - 0.5) * 0.5;
        }
    }

    @Override
    public void render(VertexConsumer consumer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        float particleX = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float particleY = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float particleZ = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        int light = LightTexture.FULL_BRIGHT;
        
        PoseStack poseStack = new PoseStack();
        poseStack.pushPose();
        poseStack.translate(particleX, particleY, particleZ);

        double distanceFromCam = new Vec3(particleX, particleY, particleZ).length();
        double inc = Mth.clamp(distanceFromCam / 32f, 0, 5f);

        poseStack.translate(0, (1 + inc / 4f) * Mth.lerp(partialTicks, this.prevVisualDY, this.visualDY), 0);
        
        float fadeout = Mth.lerp(partialTicks, this.prevFadeout, this.fadeout);
        float defScale = 0.006f;
        float scale = (float) (defScale * distanceFromCam);

        if (this.isCrit) {
            scale *= 1.5f;
        }
        
        poseStack.mulPose(camera.rotation());

        poseStack.translate((1 + inc) * Mth.lerp(partialTicks, this.prevVisualDX, this.visualDX), 0, 0);
        poseStack.scale(-scale, -scale, scale);
        poseStack.translate(0, (4d * (1 - fadeout)), 0);
        poseStack.scale(fadeout, fadeout, fadeout);
        poseStack.translate(0, -distanceFromCam / 10d, 0);

        var buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        
        float x1 = 0.5f - fontRenderer.width(text) / 2f;

        fontRenderer.drawInBatch(
            text, x1, 0, color, false,
            poseStack.last().pose(), buffer, Font.DisplayMode.NORMAL, 0, light
        );

        poseStack.translate(1, 1, +0.03);
        fontRenderer.drawInBatch(
            text, x1, 0, darkColor, false,
            poseStack.last().pose(), buffer, Font.DisplayMode.NORMAL, 0, light
        );

        buffer.endBatch();
        poseStack.popPose();
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float length = 6;
            this.prevFadeout = this.fadeout;
            this.fadeout = this.age > (lifetime - length) ? ((float) lifetime - this.age) / length : 1;

            this.prevVisualDY = this.visualDY;
            this.visualDY += (float) this.yd;
            this.prevVisualDX = this.visualDX;
            this.visualDX += (float) this.xd;

            if (Math.sqrt(Mth.square(this.visualDX * 1.5) + Mth.square(this.visualDY - 1)) < 1.9 - 1) {
                this.yd = this.yd / 2;
            } else {
                this.yd = 0;
                this.xd = 0;
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    private int parseColor(String colorString) {
        try {
            if (colorString.startsWith("#")) {
                colorString = colorString.substring(1);
            }
            return (int) Long.parseLong(colorString, 16);
        } catch (Exception e) {
            return this.isCrit ? 0xFF0000 : 0xFFFFFF;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        public Factory(SpriteSet spriteSet) {
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new DamageNumberParticle(worldIn, x, y, z, xSpeed, zSpeed);
        }
    }
}