package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.record.Class_0610;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32C;

public final class Class_0612 implements MioAPI {
   public static final Class_0809 field_1891 = Class_0809.method_740();
   public static final Class_0809 field_1892 = Class_0809.method_740();
   public static final List<Class_0610> field_1893 = new ObjectArrayList();

   public Class_0612() {
      super();
   }

   @Subscribe(
      method_800 = -9999
   )
   public static void method_2(Event_3 var0) {
      method_474();
      RenderSystem.enablePolygonOffset();
      RenderSystem.polygonOffset(1.0F, -1500000.0F);
      GL20.glDepthRange(0.0, 0.1);
      Class_0745.method_474();
      GL20.glDepthRange(0.0, 1.0);
      RenderSystem.polygonOffset(1.0F, 1500000.0F);
      RenderSystem.disablePolygonOffset();
   }

   public static void method_9(MatrixStack var0, BlockPos var1, Color var2) {
      method_5(var0, new Box(var1), var2);
   }

   public static void method_5(MatrixStack var0, Box var1, Color var2) {
      if (var2.getAlpha() != 0) {
         float var3 = (float)(var1.minX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
         float var4 = (float)(var1.minY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
         float var5 = (float)(var1.minZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
         float var6 = (float)(var1.maxX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
         float var7 = (float)(var1.maxY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
         float var8 = (float)(var1.maxZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
         int var9 = method_4(var2).hashCode();
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var5).color(var9);
      }
   }

   public static void method_7(MatrixStack var0, Box var1, Color var2) {
      if (var2.getAlpha() != 0) {
         float var3 = (float)(var1.minX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
         float var4 = (float)(var1.minY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
         float var5 = (float)(var1.minZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
         float var6 = (float)(var1.maxX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
         float var7 = (float)(var1.maxY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
         float var8 = (float)(var1.maxZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
         int var9 = var2.hashCode();
         int var10 = Class_1081.method_5(var2, 0);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var5).color(var10);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var5).color(var10);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var5).color(var10);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var8).color(var10);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var6, var7, var8).color(var10);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var8).color(var10);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var5).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var4, var8).color(var9);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var8).color(var10);
         field_1891.vertex(var0.peek().getPositionMatrix(), var3, var7, var5).color(var10);
      }
   }

   public static void method_2(MatrixStack var0, BlockPos var1, Color var2, float var3) {
      method_2(var0, new Box(var1), var2, var3);
   }

   public static void method_2(MatrixStack var0, Box var1, Color var2, float var3) {
      if (var2.getAlpha() != 0) {
         if (!(var3 <= 0.0F)) {
            field_1893.add(Class_0610.method_9(var0, var1, method_4(var2), var3));
         }
      }
   }

   public static void method_2(Class_0610 var0) {
      Matrix4f var1 = var0.field_1885;
      Matrix3f var2 = var0.field_1886;
      float var3 = (float)(var0.field_1887.minX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var4 = (float)(var0.field_1887.minY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var5 = (float)(var0.field_1887.minZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      float var6 = (float)(var0.field_1887.maxX - field_4219.getEntityRenderDispatcher().camera.getPos().getX());
      float var7 = (float)(var0.field_1887.maxY - field_4219.getEntityRenderDispatcher().camera.getPos().getY());
      float var8 = (float)(var0.field_1887.maxZ - field_4219.getEntityRenderDispatcher().camera.getPos().getZ());
      int var9 = var0.field_1888.hashCode();
      field_1892.vertex(var1, var3, var4, var5).color(var9);
      field_1892.vertex(var1, var6, var4, var5).color(var9);
      field_1892.vertex(var1, var3, var4, var5).color(var9);
      field_1892.vertex(var1, var3, var7, var5).color(var9);
      field_1892.vertex(var1, var3, var4, var5).color(var9);
      field_1892.vertex(var1, var3, var4, var8).color(var9);
      field_1892.vertex(var1, var6, var4, var5).color(var9);
      field_1892.vertex(var1, var6, var7, var5).color(var9);
      field_1892.vertex(var1, var6, var7, var5).color(var9);
      field_1892.vertex(var1, var3, var7, var5).color(var9);
      field_1892.vertex(var1, var3, var7, var5).color(var9);
      field_1892.vertex(var1, var3, var7, var8).color(var9);
      field_1892.vertex(var1, var3, var7, var8).color(var9);
      field_1892.vertex(var1, var3, var4, var8).color(var9);
      field_1892.vertex(var1, var3, var4, var8).color(var9);
      field_1892.vertex(var1, var6, var4, var8).color(var9);
      field_1892.vertex(var1, var6, var4, var8).color(var9);
      field_1892.vertex(var1, var6, var4, var5).color(var9);
      field_1892.vertex(var1, var3, var7, var8).color(var9);
      field_1892.vertex(var1, var6, var7, var8).color(var9);
      field_1892.vertex(var1, var6, var4, var8).color(var9);
      field_1892.vertex(var1, var6, var7, var8).color(var9);
      field_1892.vertex(var1, var6, var7, var5).color(var9);
      field_1892.vertex(var1, var6, var7, var8).color(var9);
   }

   public static Color method_4(Color var0) {
      return method_2(var0, RenderSystem.getShaderColor());
   }

   public static Color method_2(Color var0, float[] var1) {
      return new Color(
         (int)((float)var0.getRed() * var1[0]),
         (int)((float)var0.getGreen() * var1[1]),
         (int)((float)var0.getBlue() * var1[2]),
         (int)((float)var0.getAlpha() * var1[3])
      );
   }

   public static void method_474() {
      RenderUtil.field_2672.method_780();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      field_1891.method_529();
      field_1893.sort(Comparator.comparing(var0x -> var0x.field_1889));
      float var0 = 0.0F;

      for (Class_0610 var2 : field_1893) {
         if (var2.field_1889 != var0) {
            method_623();
         }

         var0 = var2.field_1889;
         GL32C.glLineWidth(var0);
         method_2(var2);
      }

      field_1893.clear();
      method_623();
      RenderUtil.field_2672.method_782();
   }

   public static void method_623() {
      field_1892.method_529();
      GL32C.glLineWidth(1.0F);
   }

   static {
      field_4220.method_5(Class_0612.class);
      field_1891.method_2(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
      field_1892.method_2(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
   }
}
