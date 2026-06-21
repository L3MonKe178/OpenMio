package me.mioclient.internal;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;

public class Class_1299 extends Framebuffer {
   public static Class_1299 field_4204;

   public Class_1299(int var1, int var2) {
      super(false);
      RenderSystem.assertOnRenderThreadOrInit();
      this.resize(var1, var2, true);
      this.setClearColor(0.0F, 0.0F, 0.0F, 0.0F);
   }

   public static Class_1299 method_1165() {
      if (field_4204 == null) {
         field_4204 = new Class_1299(MinecraftClient.getInstance().getFramebuffer().textureWidth, MinecraftClient.getInstance().getFramebuffer().textureHeight);
      }

      return field_4204;
   }

   public static void method_78(Runnable var0) {
      Framebuffer var1 = MinecraftClient.getInstance().getFramebuffer();
      RenderSystem.assertOnRenderThreadOrInit();
      Class_1299 var2 = method_1165();
      if (var2.textureWidth != var1.textureWidth || var2.textureHeight != var1.textureHeight) {
         var2.resize(var1.textureWidth, var1.textureHeight, false);
      }

      GlStateManager._glBindFramebuffer(36009, var2.fbo);
      var2.beginWrite(true);
      var0.run();
      var2.endWrite();
      GlStateManager._glBindFramebuffer(36009, var1.fbo);
      var1.beginWrite(false);
   }

   public static void method_16(float var0) {
      Framebuffer var1 = MinecraftClient.getInstance().getFramebuffer();
      Class_1299 var2 = method_1165();
      Class_0675.field_2158.method_9("MaskSampler", var2);
      Class_0675.field_2158.method_2("Radius", var0);
      Class_0675.field_2158.method_31(Class_0838.method_776());
      GlStateManager._glBindFramebuffer(36009, var2.fbo);
      var2.clear(true);
      GlStateManager._glBindFramebuffer(36009, var1.fbo);
      var1.beginWrite(true);
   }

   public static void method_2(Runnable var0, float var1) {
      method_78(var0);
      method_16(var1);
   }
}
