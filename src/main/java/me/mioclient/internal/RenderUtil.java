package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import me.mioclient.api.MioAPI;
import me.mioclient.enum_.Class_0650;
import me.mioclient.module.client.UIModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.MatrixStack.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.ColorHelper.Argb;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;

public class RenderUtil implements MioAPI {
   public static final RenderUtil field_2672 = new RenderUtil();
   public static float field_2673;
   public static MatrixStack field_2674;

   public RenderUtil() {
      super();
   }

   public static float method_776() {
      return field_2673;
   }

   public static void method_39(float var0) {
      field_2673 = var0;
   }

   public static MatrixStack method_10() {
      return field_2674;
   }

   public static void method_2(MatrixStack var0) {
      field_2674 = var0;
   }

   public void method_2(MatrixStack var1, float var2, float var3, float var4, float var5, Color var6) {
      if (var4 < var2) {
         float var7 = var2;
         var2 = var4;
         var4 = var7;
      }

      if (var5 < var3) {
         float var8 = var3;
         var3 = var5;
         var5 = var8;
      }

      this.method_2(var1, var2, var4, var3, var6);
      this.method_9(var1, var4, var3, var5, var6);
      this.method_2(var1, var2, var4, var5, var6);
      this.method_9(var1, var2, var3, var5, var6);
   }

   public void method_2(MatrixStack var1, float var2, float var3, float var4, float var5, Color var6, float var7) {
      this.method_2(var1, var2, var4, var3, var6, var7);
      this.method_9(var1, var4, var3, var5, var6, var7);
      this.method_2(var1, var2, var4, var5, var6, var7);
      this.method_9(var1, var2, var3, var5, var6, var7);
   }

   public void method_2(MatrixStack var1, float var2, float var3, float var4, Color var5) {
      if (var3 < var2) {
         float var6 = var2;
         var2 = var3;
         var3 = var6;
      }

      this.method_9(var1, var2, var4, var3 + 1.0F, var4 + 1.0F, var5);
   }

   public void method_9(MatrixStack var1, float var2, float var3, float var4, Color var5) {
      if (var4 < var3) {
         float var6 = var3;
         var3 = var4;
         var4 = var6;
      }

      this.method_9(var1, var2, var3 + 1.0F, var2 + 1.0F, var4, var5);
   }

   public void method_2(MatrixStack var1, float var2, float var3, float var4, Color var5, float var6) {
      if (var3 < var2) {
         float var7 = var2;
         var2 = var3;
         var3 = var7;
      }

      this.method_9(var1, var2, var4, var3 + var6, var4 + var6, var5);
   }

   public void method_9(MatrixStack var1, float var2, float var3, float var4, Color var5, float var6) {
      if (var4 < var3) {
         float var7 = var3;
         var3 = var4;
         var4 = var7;
      }

      this.method_9(var1, var2, var3 + var6, var2 + var6, var4, var5);
   }

   public void method_2(MatrixStack var1, float var2, float var3, float var4, float var5, int var6) {
      if (var2 < var4) {
         float var7 = var2;
         var2 = var4;
         var4 = var7;
      }

      if (var3 < var5) {
         float var13 = var3;
         var3 = var5;
         var5 = var13;
      }

      float var8 = (float)(var6 >> 24 & 0xFF) / 255.0F;
      float var9 = (float)(var6 >> 16 & 0xFF) / 255.0F;
      float var10 = (float)(var6 >> 8 & 0xFF) / 255.0F;
      float var11 = (float)(var6 & 0xFF) / 255.0F;
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      BufferBuilder var12 = Tessellator.getInstance().begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
      var12.vertex(var1.peek().getPositionMatrix(), var2, var5, 0.0F).color(var9, var10, var11, var8);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var5, 0.0F).color(var9, var10, var11, var8);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var3, 0.0F).color(var9, var10, var11, var8);
      var12.vertex(var1.peek().getPositionMatrix(), var2, var3, 0.0F).color(var9, var10, var11, var8);
      BufferRenderer.drawWithGlobalProgram(var12.end());
      RenderSystem.disableBlend();
   }

   public void method_9(MatrixStack var1, float var2, float var3, float var4, float var5, Color var6) {
      this.method_2(var1, var2, var3, var4, var5, var6.hashCode());
   }

   public void method_2(MatrixStack var1, int var2, int var3, int var4, int var5, Color var6, Color var7) {
      RenderSystem.enableBlend();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      Tessellator var8 = Tessellator.getInstance();
      BufferBuilder var9 = Tessellator.getInstance().begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
      this.method_2(var1.peek().getPositionMatrix(), var9, var2, var3, var4, var5, 0, var6.hashCode(), var7.hashCode());
      BufferRenderer.drawWithGlobalProgram(var9.end());
      RenderSystem.disableBlend();
   }

   public void method_2(Matrix4f var1, VertexConsumer var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
      float var10 = (float)Argb.getAlpha(var8) / 255.0F;
      float var11 = (float)Argb.getRed(var8) / 255.0F;
      float var12 = (float)Argb.getGreen(var8) / 255.0F;
      float var13 = (float)Argb.getBlue(var8) / 255.0F;
      float var14 = (float)Argb.getAlpha(var9) / 255.0F;
      float var15 = (float)Argb.getRed(var9) / 255.0F;
      float var16 = (float)Argb.getGreen(var9) / 255.0F;
      float var17 = (float)Argb.getBlue(var9) / 255.0F;
      var2.vertex(var1, (float)var3, (float)var4, (float)var7).color(var11, var12, var13, var10);
      var2.vertex(var1, (float)var3, (float)var6, (float)var7).color(var15, var16, var17, var14);
      var2.vertex(var1, (float)var5, (float)var6, (float)var7).color(var15, var16, var17, var14);
      var2.vertex(var1, (float)var5, (float)var4, (float)var7).color(var11, var12, var13, var10);
   }

   public int method_263(String var1) {
      return field_4219.textRenderer.getWidth(var1);
   }

   public int method_2(Text var1) {
      return field_4219.textRenderer.getWidth(var1);
   }

   public double method_777() {
      return (double)field_4219.getWindow().getScaledWidth();
   }

   public double method_778() {
      return (double)field_4219.getWindow().getScaledHeight();
   }

   public void method_2(MatrixStack var1, Box var2, Color var3) {
      float var4 = (float)(var2.minX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var5 = (float)(var2.minY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var6 = (float)(var2.minZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      float var7 = (float)(var2.maxX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var8 = (float)(var2.maxY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var9 = (float)(var2.maxZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      this.method_780();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      BufferBuilder var10 = Tessellator.getInstance().begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
      var10.vertex(var1.peek().getPositionMatrix(), var4, var5, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var5, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var5, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var5, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var8, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var8, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var8, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var8, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var5, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var8, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var8, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var5, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var5, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var8, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var8, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var5, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var5, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var5, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var7, var8, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var8, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var5, var6).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var5, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var8, var9).color(var3.getRGB());
      var10.vertex(var1.peek().getPositionMatrix(), var4, var8, var6).color(var3.getRGB());
      BufferRenderer.drawWithGlobalProgram(var10.end());
      this.method_782();
   }

   public void method_9(MatrixStack var1, Box var2, Color var3) {
      float var4 = (float)(var2.minX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var5 = (float)(var2.minY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var6 = (float)(var2.minZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      float var7 = (float)(var2.maxX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var8 = (float)(var2.maxY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var9 = (float)(var2.maxZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      int var10 = var3.hashCode();
      int var11 = Class_1081.method_5(var3, 0);
      this.method_780();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      BufferBuilder var12 = Tessellator.getInstance().begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var5, var6).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var5, var6).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var5, var9).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var5, var9).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var5, var6).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var8, var6).color(var11);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var8, var6).color(var11);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var5, var6).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var5, var6).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var8, var6).color(var11);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var8, var9).color(var11);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var5, var9).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var5, var9).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var5, var9).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var7, var8, var9).color(var11);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var8, var9).color(var11);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var5, var6).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var5, var9).color(var10);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var8, var9).color(var11);
      var12.vertex(var1.peek().getPositionMatrix(), var4, var8, var6).color(var11);
      BufferRenderer.drawWithGlobalProgram(var12.end());
      this.method_782();
   }

   public void method_2(MatrixStack var1, Vec3d var2, Color var3) {
      this.method_2(var1, Box.from(var2), var3);
   }

   public void method_2(MatrixStack var1, BlockPos var2, Color var3) {
      this.method_2(var1, new Box(var2), var3);
   }

   public void method_2(MatrixStack var1, Box var2, Color var3, double var4) {
      float var6 = (float)(var2.minX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var7 = (float)(var2.minY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var8 = (float)(var2.minZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      float var9 = (float)(var2.maxX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var10 = (float)(var2.maxY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var11 = (float)(var2.maxZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      Tessellator var12 = Tessellator.getInstance();
      this.method_780();
      RenderSystem.lineWidth((float)var4);
      RenderSystem.setShader(GameRenderer::getRenderTypeLinesProgram);
      GL20.glEnable(2848);
      GL20.glHint(3154, 4354);
      RenderSystem.defaultBlendFunc();
      BufferBuilder var13 = Tessellator.getInstance().begin(DrawMode.LINES, VertexFormats.LINES);
      WorldRenderer.drawBox(
         var1,
         var13,
         (double)var6,
         (double)var7,
         (double)var8,
         (double)var9,
         (double)var10,
         (double)var11,
         (float)var3.getRed() / 255.0F,
         (float)var3.getGreen() / 255.0F,
         (float)var3.getBlue() / 255.0F,
         (float)var3.getAlpha() / 255.0F
      );
      BufferRenderer.drawWithGlobalProgram(var13.end());
      GL20.glDisable(2848);
      this.method_782();
   }

   public void method_2(MatrixStack var1, Vec3d var2, Color var3, double var4) {
      this.method_2(var1, Box.from(var2), var3, var4);
   }

   public void method_2(MatrixStack var1, BlockPos var2, Color var3, double var4) {
      this.method_2(var1, new Box(var2), var3, var4);
   }

   public void method_2(MatrixStack var1, Vec3d var2, Vec3d var3, Color var4, float var5) {
      float var6 = (float)(var2.x - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var7 = (float)(var2.y - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var8 = (float)(var2.z - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      float var9 = (float)(var3.x - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var10 = (float)(var3.y - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var11 = (float)(var3.z - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      Entry var12 = var1.peek();
      this.method_780();
      RenderSystem.lineWidth(var5);
      RenderSystem.setShader(GameRenderer::getRenderTypeLinesProgram);
      GL20.glEnable(2848);
      GL20.glHint(3154, 4354);
      RenderSystem.defaultBlendFunc();
      BufferBuilder var13 = Tessellator.getInstance().begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
      var13.vertex(var1.peek().getPositionMatrix(), var6, var7, var8).color(var4.getRGB()).normal(var12, var5, 0.0F, 0.0F);
      var13.vertex(var1.peek().getPositionMatrix(), var9, var10, var11).color(var4.getRGB()).normal(var12, var5, 0.0F, 0.0F);
      var13.vertex(var1.peek().getPositionMatrix(), var6, var7, var8).color(var4.getRGB()).normal(var12, 0.0F, var5, 0.0F);
      var13.vertex(var1.peek().getPositionMatrix(), var9, var10, var11).color(var4.getRGB()).normal(var12, 0.0F, var5, 0.0F);
      var13.vertex(var1.peek().getPositionMatrix(), var6, var7, var8).color(var4.getRGB()).normal(var12, 0.0F, 0.0F, var5);
      var13.vertex(var1.peek().getPositionMatrix(), var9, var10, var11).color(var4.getRGB()).normal(var12, 0.0F, 0.0F, var5);
      BufferRenderer.drawWithGlobalProgram(var13.end());
      GL20.glDisable(2848);
      this.method_782();
   }

   public void method_2(MatrixStack var1, Vec3d var2, Vec3d var3, Color var4) {
      float var5 = (float)(var2.x - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var6 = (float)(var2.y - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var7 = (float)(var2.z - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      float var8 = (float)(var3.x - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var9 = (float)(var3.y - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var10 = (float)(var3.z - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      field_2672.method_780();
      BufferBuilder var11 = Tessellator.getInstance().begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
      var11.vertex(var1.peek().getPositionMatrix(), var5, var6, var7).color(var4.hashCode());
      var11.vertex(var1.peek().getPositionMatrix(), var8, var9, var10).color(var4.hashCode());
      BufferRenderer.drawWithGlobalProgram(var11.end());
      field_2672.method_782();
   }

   public void method_779() {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
   }

   public void method_780() {
      this.method_779();
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      RenderSystem.disableCull();
   }

   public void method_781() {
      RenderSystem.disableBlend();
   }

   public void method_782() {
      this.method_781();
      RenderSystem.enableDepthTest();
      RenderSystem.depthMask(true);
      RenderSystem.enableCull();
   }

   public void method_38(Runnable var1) {
      if (UIModule.field_2843.field_2847.getValue() != Class_0650.NONE) {
         int var2 = UIModule.field_2843.field_2847.getValue().method_658();
         Class_0595.method_2((var2 & var2 - 1) == 0 ? var2 : 2, var1);
      } else {
         var1.run();
      }
   }

   public void method_2(MatrixStack var1, Vec3d var2, float var3, float var4, float var5, float var6, double var7, Color var9) {
      var1.push();
      method_2(var1, var2);
      RenderSystem.defaultBlendFunc();
      var1.translate(var3, var4, 0.0F);
      var1.multiply(field_4219.getEntityRenderDispatcher().getRotation());
      var1.scale(0.025F * (float)var7, -0.025F * (float)var7, (float)(0.025F * var7));
      int var10 = (int)(var5 / 2.0F);
      RenderSystem.disableDepthTest();
      this.method_9(var1, (float)(-var10), 0.0F, (float)((int)(var5 - (float)var10)), (float)((int)var6), var9);
      RenderSystem.enableDepthTest();
      var1.pop();
   }

   public void method_2(DrawContext var1, Vec3d var2, float var3, float var4, double var5, Color var7, Identifier var8) {
      MatrixStack var9 = var1.getMatrices();
      var9.push();
      method_2(var9, var2);
      Camera var10 = field_4219.gameRenderer.getCamera();
      var9.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-var10.getYaw()));
      var9.multiply(RotationAxis.POSITIVE_X.rotationDegrees(var10.getPitch()));
      RenderSystem.defaultBlendFunc();
      var9.scale(0.025F * (float)var5, -0.025F * (float)var5, 1.0F);
      int var11 = (int)(var3 / 2.0F);
      RenderSystem.disableDepthTest();
      RenderSystem.disableCull();
      RenderSystem.setShader(GameRenderer::getPositionTexColorProgram);
      RenderSystem.setShaderColor((float)var7.getRed() / 255.0F, (float)var7.getGreen() / 255.0F, (float)var7.getBlue() / 255.0F, 1.0F);
      var1.drawTexture(var8, -var11, -var11, 0, 0, (int)var3, (int)var4);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableDepthTest();
      var9.pop();
   }

   public void method_9(MatrixStack var1, Vec3d var2, float var3, float var4, float var5, float var6, double var7, Color var9) {
      var1.push();
      method_2(var1, var2);
      Camera var10 = field_4219.gameRenderer.getCamera();
      var1.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-var10.getYaw()));
      var1.multiply(RotationAxis.POSITIVE_X.rotationDegrees(var10.getPitch()));
      RenderSystem.defaultBlendFunc();
      var1.translate(var3, var4, 0.0F);
      var1.scale(0.025F * (float)var7, -0.025F * (float)var7, 1.0F);
      int var11 = (int)(var5 / 2.0F);
      RenderSystem.disableDepthTest();
      this.method_2(var1, (float)(-var11), 0.0F, (float)((int)(var5 - (float)var11)), (float)((int)var6), var9);
      RenderSystem.enableDepthTest();
      var1.pop();
   }

   public void method_2(DrawContext var1, String var2, Vec3d var3, float var4, float var5, double var6, Color var8, boolean var9) {
      this.method_2(var1, var2, var3, var4, var5, -FontRenderer.field_3143.method_221(var2) / 2.0F, 0.0F, var6, var8, var9);
   }

   public void method_2(DrawContext var1, String var2, Vec3d var3, float var4, float var5, float var6, float var7, double var8, Color var10, boolean var11) {
      var1.getMatrices().push();
      method_2(var1.getMatrices(), var3);
      RenderSystem.defaultBlendFunc();
      var1.getMatrices().translate(var4, var5, 0.0F);
      var1.getMatrices().multiply(field_4219.getEntityRenderDispatcher().getRotation());
      var1.getMatrices().scale(0.025F * (float)var8, -0.025F * (float)var8, (float)(0.025F * var8));
      if (var11) {
         FontRenderer.field_3143.method_9(var1, var2, var6, var7, var10);
      } else {
         FontRenderer.field_3143.method_2(var1, var2, var6, var7, var10);
      }

      var1.getMatrices().pop();
   }

   public void method_2(MatrixStack var1, BufferBuilder var2, Vec3d var3, Vec3d var4, int var5, int var6) {
      float var7 = (float)(var3.getX() - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var8 = (float)(var3.getY() - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var9 = (float)(var3.getZ() - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      float var10 = (float)(var4.getX() - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var11 = (float)(var4.getY() - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var12 = (float)(var4.getZ() - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      var2.vertex(var1.peek().getPositionMatrix(), var7, var8, var9).color(var5).normal(var1.peek(), var10 - var7, var11 - var8, var12 - var9);
      var2.vertex(var1.peek().getPositionMatrix(), var10, var11, var12).color(var6).normal(var1.peek(), var10 - var7, var11 - var8, var12 - var9);
   }

   public void method_2(MatrixStack var1, BufferBuilder var2, Box var3, int var4) {
      float var5 = (float)(var3.minX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var6 = (float)(var3.minY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var7 = (float)(var3.minZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      float var8 = (float)(var3.maxX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var9 = (float)(var3.maxZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      Matrix4f var10 = var1.peek().getPositionMatrix();
      var2.vertex(var10, var5, var6, var7).color(var4);
      var2.vertex(var10, var8, var6, var7).color(var4);
      var2.vertex(var10, var8, var6, var9).color(var4);
      var2.vertex(var10, var5, var6, var9).color(var4);
      var2.vertex(var10, var5, var6, var7).color(var4);
   }

   public void method_2(Entity var1, float var2, MatrixStack var3, VertexConsumerProvider var4) {
      EntityRenderDispatcher var5 = field_4219.getEntityRenderDispatcher();
      Vec3d var6 = field_4219.gameRenderer.getCamera().getPos();
      double var7 = MathHelper.lerp((double)var2, var1.lastRenderX, var1.getX());
      double var9 = MathHelper.lerp((double)var2, var1.lastRenderY, var1.getY());
      double var11 = MathHelper.lerp((double)var2, var1.lastRenderZ, var1.getZ());
      float var13 = MathHelper.lerp(var2, var1.prevYaw, var1.getYaw());
      var5.render(var1, var7 - var6.x, var9 - var6.y, var11 - var6.z, var13, var2, var3, var4, var5.getLight(var1, var2));
   }

   public void method_2(Matrix4f var1, float var2, float var3, float var4, float var5, int var6, int var7, int var8, int var9) {
      float[] var10 = new float[]{(float)(var6 >> 16 & 0xFF) / 255.0F, (float)(var6 >> 8 & 0xFF) / 255.0F, (float)(var6 & 0xFF) / 255.0F};
      float[] var11 = new float[]{(float)(var7 >> 16 & 0xFF) / 255.0F, (float)(var7 >> 8 & 0xFF) / 255.0F, (float)(var7 & 0xFF) / 255.0F};
      float[] var12 = new float[]{(float)(var8 >> 16 & 0xFF) / 255.0F, (float)(var8 >> 8 & 0xFF) / 255.0F, (float)(var8 & 0xFF) / 255.0F};
      float[] var13 = new float[]{(float)(var9 >> 16 & 0xFF) / 255.0F, (float)(var9 >> 8 & 0xFF) / 255.0F, (float)(var9 & 0xFF) / 255.0F};
      this.method_779();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      BufferBuilder var14 = Tessellator.getInstance().begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
      var14.vertex(var1, var2, var3 + var5, 0.0F).color(var13[0], var13[1], var13[2], 1.0F);
      var14.vertex(var1, var2 + var4, var3 + var5, 0.0F).color(var12[0], var12[1], var12[2], 1.0F);
      var14.vertex(var1, var2 + var4, var3, 0.0F).color(var11[0], var11[1], var11[2], 1.0F);
      var14.vertex(var1, var2, var3, 0.0F).color(var10[0], var10[1], var10[2], 1.0F);
      BufferRenderer.drawWithGlobalProgram(var14.end());
      this.method_781();
   }

   public void method_2(Matrix4f var1, float var2, float var3, float var4, float var5, int... var6) {
      if (var6.length >= 2) {
         float[][] var7 = new float[var6.length][4];

         for (int var8 = 0; var8 < var6.length; var8++) {
            var7[var8] = new float[]{
               (float)(var6[var8] >> 24 & 0xFF) / 255.0F,
               (float)(var6[var8] >> 16 & 0xFF) / 255.0F,
               (float)(var6[var8] >> 8 & 0xFF) / 255.0F,
               (float)(var6[var8] & 0xFF) / 255.0F
            };
         }

         this.method_779();
         RenderSystem.setShader(GameRenderer::getPositionColorProgram);
         float var12 = var4 / (float)var6.length;

         for (int var9 = 1; var9 < var6.length; var9++) {
            Tessellator var10 = Tessellator.getInstance();
            BufferBuilder var11 = Tessellator.getInstance().begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            var11.vertex(var1, var2 + (var9 == var6.length - 1 ? var4 : var12 * (float)var9), var3, 0.0F)
               .color(var7[var9][1], var7[var9][2], var7[var9][3], var7[var9][0]);
            var11.vertex(var1, var2 + var12 * (float)(var9 - 1), var3, 0.0F).color(var7[var9 - 1][1], var7[var9 - 1][2], var7[var9 - 1][3], var7[var9 - 1][0]);
            var11.vertex(var1, var2 + var12 * (float)(var9 - 1), var3 + var5, 0.0F)
               .color(var7[var9 - 1][1], var7[var9 - 1][2], var7[var9 - 1][3], var7[var9 - 1][0]);
            var11.vertex(var1, var2 + (var9 == var6.length - 1 ? var4 : var12 * (float)var9), var3 + var5, 0.0F)
               .color(var7[var9][1], var7[var9][2], var7[var9][3], var7[var9][0]);
            BufferRenderer.drawWithGlobalProgram(var11.end());
         }

         this.method_781();
      }
   }

   public static void method_2(MatrixStack var0, Vec3d var1) {
      Vec3d var2 = field_4219.gameRenderer.getCamera().getPos();
      double var3 = var1.x - var2.x;
      double var5 = var1.y - var2.y;
      double var7 = var1.z - var2.z;
      var0.translate(var3, var5, var7);
   }

   public static void method_9(MatrixStack var0) {
      Camera var1 = field_4219.gameRenderer.getCamera();
      var0.multiply(RotationAxis.POSITIVE_X.rotationDegrees(var1.getPitch()));
      var0.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(var1.getYaw() + 180.0F));
   }
}
