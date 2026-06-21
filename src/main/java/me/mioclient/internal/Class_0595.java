package me.mioclient.internal;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import org.lwjgl.opengl.GL30;

public class Class_0595 extends Framebuffer {
   public static final int field_1858 = 2;
   public static final int field_1859 = GL30.glGetInteger(36183);
   public static final Map<Integer, Class_0595> field_1860 = new HashMap<>();
   public static final List<Class_0595> field_1861 = new ArrayList<>();
   public final int field_1862;
   public int field_1863;
   public int field_1864;
   public boolean field_1865;

   public Class_0595(int var1) {
      super(true);
      int var2 = Math.clamp((long)var1, 2, field_1859);
      if ((var2 & var2 - 1) != 0) {
         throw new IllegalArgumentException(String.valueOf(var2));
      } else {
         this.field_1862 = var2;
         this.setClearColor(1.0F, 1.0F, 1.0F, 0.0F);
      }
   }

   public static boolean method_608() {
      return !field_1861.isEmpty();
   }

   public static Class_0595 method_107(int var0) {
      return field_1860.computeIfAbsent(var0, var1 -> new Class_0595(var0));
   }

   public static void method_2(int var0, Runnable var1) {
      method_2(var0, MinecraftClient.getInstance().getFramebuffer(), var1);
   }

   public static void method_2(int var0, Framebuffer var1, Runnable var2) {
      RenderSystem.assertOnRenderThreadOrInit();
      Class_0595 var3 = method_107(var0);
      var3.resize(var1.textureWidth, var1.textureHeight, true);
      GlStateManager._glBindFramebuffer(36008, var1.fbo);
      GlStateManager._glBindFramebuffer(36009, var3.fbo);
      GlStateManager._glBlitFrameBuffer(0, 0, var3.textureWidth, var3.textureHeight, 0, 0, var3.textureWidth, var3.textureHeight, 16384, 9729);
      var3.beginWrite(true);
      var2.run();
      var3.endWrite();
      GlStateManager._glBindFramebuffer(36008, var3.fbo);
      GlStateManager._glBindFramebuffer(36009, var1.fbo);
      GlStateManager._glBlitFrameBuffer(0, 0, var3.textureWidth, var3.textureHeight, 0, 0, var3.textureWidth, var3.textureHeight, 16384, 9729);
      var3.clear(true);
      var1.beginWrite(false);
   }

   public void resize(int width, int height, boolean getError) {
      if (this.textureWidth != width || this.textureHeight != height) {
         super.resize(width, height, getError);
      }
   }

   public void initFbo(int width, int height, boolean getError) {
      RenderSystem.assertOnRenderThreadOrInit();
      int var4 = RenderSystem.maxSupportedTextureSize();
      if (width > 0 && width <= var4 && height > 0 && height <= var4) {
         this.viewportWidth = width;
         this.viewportHeight = height;
         this.textureWidth = width;
         this.textureHeight = height;
         this.fbo = GlStateManager.glGenFramebuffers();
         GlStateManager._glBindFramebuffer(36160, this.fbo);
         this.field_1863 = GlStateManager.glGenRenderbuffers();
         GlStateManager._glBindRenderbuffer(36161, this.field_1863);
         GL30.glRenderbufferStorageMultisample(36161, this.field_1862, 32856, width, height);
         GlStateManager._glBindRenderbuffer(36161, 0);
         this.field_1864 = GlStateManager.glGenRenderbuffers();
         GlStateManager._glBindRenderbuffer(36161, this.field_1864);
         GL30.glRenderbufferStorageMultisample(36161, this.field_1862, 6402, width, height);
         GlStateManager._glBindRenderbuffer(36161, 0);
         GL30.glFramebufferRenderbuffer(36160, 36064, 36161, this.field_1863);
         GL30.glFramebufferRenderbuffer(36160, 36096, 36161, this.field_1864);
         this.colorAttachment = MinecraftClient.getInstance().getFramebuffer().getColorAttachment();
         this.depthAttachment = MinecraftClient.getInstance().getFramebuffer().getDepthAttachment();
         this.checkFramebufferStatus();
         this.clear(getError);
         this.endRead();
      } else {
         throw new IllegalArgumentException("%d x %d (Out of bounds).".formatted(width, height));
      }
   }

   public void delete() {
      RenderSystem.assertOnRenderThreadOrInit();
      this.endRead();
      this.endWrite();
      if (this.fbo > -1) {
         GlStateManager._glBindFramebuffer(36160, 0);
         GlStateManager._glDeleteFramebuffers(this.fbo);
         this.fbo = -1;
      }

      if (this.field_1863 > -1) {
         GlStateManager._glDeleteRenderbuffers(this.field_1863);
         this.field_1863 = -1;
      }

      if (this.field_1864 > -1) {
         GlStateManager._glDeleteRenderbuffers(this.field_1864);
         this.field_1864 = -1;
      }

      this.colorAttachment = -1;
      this.depthAttachment = -1;
      this.textureWidth = -1;
      this.textureHeight = -1;
   }

   public void beginWrite(boolean setViewport) {
      super.beginWrite(setViewport);
      if (!this.field_1865) {
         field_1861.add(this);
         this.field_1865 = true;
      }
   }

   public void endWrite() {
      super.endWrite();
      if (this.field_1865) {
         this.field_1865 = false;
         field_1861.remove(this);
      }
   }
}
