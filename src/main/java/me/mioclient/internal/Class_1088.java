package me.mioclient.internal;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.GlStateManager;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import me.mioclient.api.Class_0637;
import me.mioclient.api.Class_1309;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL32C;

public class Class_1088 implements Class_1309 {
   public static final FloatBuffer field_3354 = BufferUtils.createFloatBuffer(16);
   public static final Class_0637 field_3355 = method_230("DEPTH");
   public static final Class_0637 field_3356 = method_230("BLEND");
   public static final Class_0637 field_3357 = method_230("CULL");
   public static final Class_0637 field_3358 = method_230("SCISSOR");
   public static boolean field_3359;
   public static boolean field_3360;
   public static boolean field_3361;
   public static boolean field_3362;
   public static boolean field_3363 = true;
   public static int field_3364;
   public static int field_3365;

   public Class_1088() {
      super();
   }

   public static void method_209(int var0) {
      GlStateManager._glBindVertexArray(var0);
      if (field_3363) {
         BufferRenderer.currentVertexBuffer = null;
      }
   }

   public static void method_263(int var0) {
      GlStateManager._glBindBuffer(34962, var0);
   }

   public static void method_221(int var0) {
      if (var0 != 0) {
         field_3365 = field_3364;
      }

      GlStateManager._glBindBuffer(34963, var0 != 0 ? var0 : field_3365);
   }

   public static void method_222(int var0) {
      GlStateManager._glBindFramebuffer(36160, var0);
   }

   public static void method_2(int var0, int var1, int var2) {
      GlStateManager._drawElements(var0, var1, var2, 0L);
   }

   public static void method_2(int var0, String var1) {
      GlStateManager.glShaderSource(var0, ImmutableList.of(var1));
   }

   public static String method_186(int var0) {
      GlStateManager.glCompileShader(var0);
      return GlStateManager.glGetShaderi(var0, 35713) == 0 ? GlStateManager.glGetShaderInfoLog(var0, 512) : null;
   }

   public static String method_9(int var0, int var1, int var2) {
      GlStateManager.glAttachShader(var0, var1);
      GlStateManager.glAttachShader(var0, var2);
      GlStateManager.glLinkProgram(var0);
      return GlStateManager.glGetProgrami(var0, 35714) == 0 ? GlStateManager.glGetProgramInfoLog(var0, 512) : null;
   }

   public static int method_9(int var0, String var1) {
      return GlStateManager._glGetUniformLocation(var0, var1);
   }

   public static void method_2(int var0, Matrix4f var1) {
      var1.get(field_3354);
      GlStateManager._glUniformMatrix4(var0, false, field_3354);
   }

   public static void method_963() {
      GlStateManager._pixelStore(3312, 0);
      GlStateManager._pixelStore(3313, 0);
      GlStateManager._pixelStore(3314, 0);
      GlStateManager._pixelStore(32878, 0);
      GlStateManager._pixelStore(3315, 0);
      GlStateManager._pixelStore(3316, 0);
      GlStateManager._pixelStore(32877, 0);
      GlStateManager._pixelStore(3317, 4);
   }

   public static void method_99(int var0) {
      GlStateManager._clearColor(0.0F, 0.0F, 0.0F, Float.intBitsToFloat(1065353216));
      GlStateManager._clear(var0, false);
   }

   public static void method_964() {
      field_3359 = field_3355.getState();
      field_3360 = field_3356.getState();
      field_3361 = field_3357.getState();
      field_3362 = field_3358.getState();
   }

   public static void method_965() {
      field_3355.set(field_3359);
      field_3356.set(field_3360);
      field_3357.set(field_3361);
      field_3358.set(field_3362);
      method_968();
   }

   public static void method_966() {
      GlStateManager._enableBlend();
      GlStateManager._blendFunc(770, 771);
   }

   public static void method_967() {
      GL32C.glEnable(2848);
      GL32C.glLineWidth(Float.intBitsToFloat(1065353216));
   }

   public static void method_968() {
      GL32C.glDisable(2848);
   }

   public static void method_38(Identifier var0) {
      GlStateManager._activeTexture(33984);
      field_4219.getTextureManager().bindTexture(var0);
   }

   public static void method_38(int var0, int var1) {
      GlStateManager._activeTexture(33984 + var1);
      GlStateManager._bindTexture(var0);
   }

   public static void method_100(int var0) {
      method_38(var0, 0);
   }

   public static void method_969() {
      GlStateManager._activeTexture(33984);
   }

   public static Class_0637 method_230(String var0) {
      try {
         Class<GlStateManager> var1 = GlStateManager.class;
         Field var2 = var1.getDeclaredField(var0);
         var2.setAccessible(true);
         Object var3 = var2.get(null);
         String var4 = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "com.mojang.blaze3d.platform.GlStateManager$class_1018");
         Field var5 = null;

         for (Field var9 : var3.getClass().getDeclaredFields()) {
            if (var9.getType().getName().equals(var4)) {
               var5 = var9;
               break;
            }
         }

         var5.setAccessible(true);
         return (Class_0637)var5.get(var3);
      } catch (IllegalAccessException | NoSuchFieldException var10) {
         var10.printStackTrace();
         return null;
      }
   }
}
