package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer.TextLayerType;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import org.joml.Matrix4f;

public class Class_0745 {
   public static final Class_0809 field_2377 = Class_0809.method_740();

   public Class_0745() {
      super();
   }

   public static void method_5(MatrixStack var0, float var1, float var2, float var3, float var4, Color var5) {
      method_4(var0, var1, var3, var2 + var4, var3 + var4, var5);
   }

   public static void method_7(MatrixStack var0, float var1, float var2, float var3, float var4, Color var5) {
      method_4(var0, var1, var2 + var4, var1 + var4, var3, var5);
   }

   public static void method_29(MatrixStack var0, float var1, float var2, float var3, float var4, Color var5) {
      method_5(var0, var1, var3, var2, 1.0F, var5);
      method_7(var0, var3, var2, var4, 1.0F, var5);
      method_5(var0, var1, var3, var4, 1.0F, var5);
      method_7(var0, var1, var2, var4, 1.0F, var5);
   }

   public static void method_2(MatrixStack var0, float var1, float var2, float var3, float var4, float var5, Color var6) {
      method_5(var0, var1, var3, var2, var5, var6);
      method_7(var0, var3, var2, var4, var5, var6);
      method_5(var0, var1, var3, var4, var5, var6);
      method_7(var0, var1, var2, var4, var5, var6);
   }

   public static void method_4(MatrixStack var0, float var1, float var2, float var3, float var4, Color var5) {
      method_9(var0, var1, var2, var3, var4, var5.hashCode());
   }

   public static void method_9(MatrixStack var0, float var1, float var2, float var3, float var4, int var5) {
      float var6 = (float)(var5 >> 24 & 0xFF) / 255.0F;
      float var7 = (float)(var5 >> 16 & 0xFF) / 255.0F;
      float var8 = (float)(var5 >> 8 & 0xFF) / 255.0F;
      float var9 = (float)(var5 & 0xFF) / 255.0F;
      if (var6 != 0.0F) {
         field_2377.vertex(var0.peek().getPositionMatrix(), var1, var4, 0.0F).color(var7, var8, var9, var6);
         field_2377.vertex(var0.peek().getPositionMatrix(), var3, var4, 0.0F).color(var7, var8, var9, var6);
         field_2377.vertex(var0.peek().getPositionMatrix(), var3, var2, 0.0F).color(var7, var8, var9, var6);
         field_2377.vertex(var0.peek().getPositionMatrix(), var1, var2, 0.0F).color(var7, var8, var9, var6);
      }
   }

   public static void method_9(Matrix4f var0, float var1, float var2, float var3, float var4, int var5, int var6, int var7, int var8) {
      float[] var9 = new float[]{(float)(var5 >> 16 & 0xFF) / 255.0F, (float)(var5 >> 8 & 0xFF) / 255.0F, (float)(var5 & 0xFF) / 255.0F};
      float[] var10 = new float[]{(float)(var6 >> 16 & 0xFF) / 255.0F, (float)(var6 >> 8 & 0xFF) / 255.0F, (float)(var6 & 0xFF) / 255.0F};
      float[] var11 = new float[]{(float)(var7 >> 16 & 0xFF) / 255.0F, (float)(var7 >> 8 & 0xFF) / 255.0F, (float)(var7 & 0xFF) / 255.0F};
      float[] var12 = new float[]{(float)(var8 >> 16 & 0xFF) / 255.0F, (float)(var8 >> 8 & 0xFF) / 255.0F, (float)(var8 & 0xFF) / 255.0F};
      field_2377.vertex(var0, var1, var2 + var4, 0.0F).color(var12[0], var12[1], var12[2], 1.0F);
      field_2377.vertex(var0, var1 + var3, var2 + var4, 0.0F).color(var11[0], var11[1], var11[2], 1.0F);
      field_2377.vertex(var0, var1 + var3, var2, 0.0F).color(var10[0], var10[1], var10[2], 1.0F);
      field_2377.vertex(var0, var1, var2, 0.0F).color(var9[0], var9[1], var9[2], 1.0F);
   }

   public static void method_9(Matrix4f var0, float var1, float var2, float var3, float var4, int... var5) {
      if (var5.length >= 2) {
         float[][] var6 = new float[var5.length][4];

         for (int var7 = 0; var7 < var5.length; var7++) {
            var6[var7] = new float[]{
               (float)(var5[var7] >> 24 & 0xFF) / 255.0F,
               (float)(var5[var7] >> 16 & 0xFF) / 255.0F,
               (float)(var5[var7] >> 8 & 0xFF) / 255.0F,
               (float)(var5[var7] & 0xFF) / 255.0F
            };
         }

         float var10 = var3 / (float)var5.length;

         for (int var8 = 1; var8 < var5.length; var8++) {
            float var9 = var8 == var5.length - 1 ? var3 : var10 * (float)var8;
            field_2377.vertex(var0, var1 + var9, var2, 0.0F).color(var6[var8][1], var6[var8][2], var6[var8][3], var6[var8][0]);
            field_2377.vertex(var0, var1 + var10 * (float)(var8 - 1), var2, 0.0F)
               .color(var6[var8 - 1][1], var6[var8 - 1][2], var6[var8 - 1][3], var6[var8 - 1][0]);
            field_2377.vertex(var0, var1 + var10 * (float)(var8 - 1), var2 + var4, 0.0F)
               .color(var6[var8 - 1][1], var6[var8 - 1][2], var6[var8 - 1][3], var6[var8 - 1][0]);
            field_2377.vertex(var0, var1 + var9, var2 + var4, 0.0F).color(var6[var8][1], var6[var8][2], var6[var8][3], var6[var8][0]);
         }
      }
   }

   public static void method_2(MatrixStack var0, String var1, int var2, int var3, int var4, boolean var5) {
      MinecraftClient.getInstance()
         .textRenderer
         .draw(
            var1,
            (float)var2,
            (float)var3,
            var4,
            var5,
            var0.peek().getPositionMatrix(),
            FontRenderer.field_3143.method_914().method_476(),
            TextLayerType.NORMAL,
            0,
            15728880
         );
   }

   public static void method_2(MatrixStack var0, OrderedText var1, int var2, int var3, int var4, boolean var5) {
      MinecraftClient.getInstance()
         .textRenderer
         .draw(
            var1,
            (float)var2,
            (float)var3,
            var4,
            var5,
            var0.peek().getPositionMatrix(),
            FontRenderer.field_3143.method_914().method_476(),
            TextLayerType.NORMAL,
            0,
            15728880
         );
   }

   public static void method_474() {
      method_30(true);
   }

   public static void method_30(boolean var0) {
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      field_2377.method_529();
      RenderSystem.disableBlend();
      if (var0) {
         FontRenderer.field_3143.method_474();
      }
   }

   static {
      field_2377.method_2(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
   }
}
