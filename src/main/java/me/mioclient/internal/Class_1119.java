package me.mioclient.internal;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import me.mioclient.api.MioAPI;
import org.apache.commons.io.IOUtils;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL32C;

public class Class_1119 implements MioAPI {
   public static Class_1119 field_3472;
   public final int field_3473;
   public final Object2IntMap<String> field_3474 = new Object2IntOpenHashMap();

   public Class_1119(String var1, String var2) {
      super();
      int var3 = GlStateManager.glCreateShader(35633);
      Class_1088.method_2(var3, this.method_186(var1));
      String var4 = Class_1088.method_186(var3);
      if (var4 != null) {
         System.err.println(new TextBuilder().method_2((Object)var4).method_2((Object)var1).method_9("Failed to compile vertex shader (\u0001): \u0001"));
         throw new RuntimeException(new TextBuilder().method_2((Object)var4).method_2((Object)var1).method_9("Failed to compile vertex shader (\u0001): \u0001"));
      } else {
         int var5 = GlStateManager.glCreateShader(35632);
         Class_1088.method_2(var5, this.method_186(var2));
         String var6 = Class_1088.method_186(var5);
         if (var6 != null) {
            System.err.println(new TextBuilder().method_2((Object)var6).method_2((Object)var2).method_9("Failed to compile fragment shader (\u0001): \u0001"));
            throw new RuntimeException(
               new TextBuilder().method_2((Object)var6).method_2((Object)var2).method_9("Failed to compile fragment shader (\u0001): \u0001")
            );
         } else {
            this.field_3473 = GlStateManager.glCreateProgram();
            String var7 = Class_1088.method_9(this.field_3473, var3, var5);
            if (var7 != null) {
               System.err.println(new TextBuilder().method_2((Object)var7).method_9("Failed to link program: \u0001"));
               throw new RuntimeException(new TextBuilder().method_2((Object)var7).method_9("Failed to link program: \u0001"));
            } else {
               GlStateManager.glDeleteShader(var3);
               GlStateManager.glDeleteShader(var5);
            }
         }
      }
   }

   public String method_186(String var1) {
      try {
         InputStream var2 = this.getClass().getResourceAsStream(new TextBuilder().method_2((Object)var1).method_9("/assets/mio/shaders\u0001"));
         return IOUtils.toString(var2, StandardCharsets.UTF_8);
      } catch (IOException var3) {
         var3.printStackTrace();
         return "";
      }
   }

   public void method_1004() {
      GlStateManager._glUseProgram(this.field_3473);
      field_3472 = this;
   }

   public int method_98(String var1) {
      if (this.field_3474.containsKey(var1)) {
         return this.field_3474.getInt(var1);
      } else {
         int var2 = Class_1088.method_9(this.field_3473, var1);
         this.field_3474.put(var1, var2);
         return var2;
      }
   }

   public void method_2(String var1, boolean var2) {
      GlStateManager._glUniform1i(this.method_98(var1), var2 ? 1 : 0);
   }

   public void method_2(String var1, int var2) {
      GlStateManager._glUniform1i(this.method_98(var1), var2);
   }

   public void method_2(String var1, double var2) {
      GL32C.glUniform1f(this.method_98(var1), (float)var2);
   }

   public void method_2(String var1, double var2, double var4) {
      GL32C.glUniform2f(this.method_98(var1), (float)var2, (float)var4);
   }

   public void method_2(String var1, Color var2) {
      GL32C.glUniform4f(
         this.method_98(var1),
         (float)var2.getRed() / Float.intBitsToFloat(1132396544),
         (float)var2.getGreen() / Float.intBitsToFloat(1132396544),
         (float)var2.getBlue() / Float.intBitsToFloat(1132396544),
         (float)var2.getAlpha() / Float.intBitsToFloat(1132396544)
      );
   }

   public void method_2(String var1, Matrix4f var2) {
      Class_1088.method_2(this.method_98(var1), var2);
   }

   public void method_1005() {
      this.method_2("u_Proj", RenderSystem.getProjectionMatrix());
      this.method_2("u_ModelView", RenderSystem.getModelViewStack());
   }
}
