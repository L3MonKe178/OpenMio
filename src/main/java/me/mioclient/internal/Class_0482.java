package me.mioclient.internal;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import me.mioclient.api.Class_0597;
import me.mioclient.api.Class_1309;
import me.mioclient.event.Event_3;
import me.mioclient.event.Subscribe;
import me.mioclient.record.Class_0661;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPart.Cuboid;
import net.minecraft.client.model.ModelPart.Quad;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector4f;

public class Class_0482 implements Class_1309 {
   public static final Vector4f field_1525 = new Vector4f();
   public static final Vector4f field_1526 = new Vector4f();
   public static final Vector4f field_1527 = new Vector4f();
   public static final Vector4f field_1528 = new Vector4f();
   public static final Map<EntityType<?>, Class_0597<?>> field_1529 = new HashMap<>();
   public static final Class_0629 field_1530 = new Class_0629();
   public static final Class_0809 field_1531 = Class_0809.method_740();
   public static final Class_0809 field_1532 = Class_0809.method_740();
   public static Color field_1533 = Color.white;
   public static Color field_1534 = Color.white;
   public static boolean field_1535;
   public static double field_1536;

   public Class_0482() {
      super();
      throw new AssertionError();
   }

   @Subscribe(
      method_800 = -9999
   )
   public static void method_9(Event_3 var0) {
      Class_0838.field_2672.method_780();
      RenderSystem.setShader(GameRenderer::getPositionColorProgram);
      field_1531.method_529();
      RenderSystem.setShader(GameRenderer::getRenderTypeLinesProgram);
      RenderSystem.lineWidth((float)field_1536);
      field_1532.method_529();
      RenderSystem.lineWidth(1.0F);
      Class_0838.field_2672.method_782();
   }

   public static void method_2(Color var0, Color var1) {
      field_1533 = var0;
      field_1534 = var1;
   }

   public static void method_16(boolean var0) {
      field_1535 = var0;
   }

   public static boolean method_505() {
      return field_1535;
   }

   public static void method_2(MatrixStack var0, Entity var1) {
      var0.push();
      Vec3d var2 = field_4219.gameRenderer.getCamera().getPos();
      float var3 = Class_0838.method_776();
      if (var1 instanceof Class_0878) {
         var3 = 1.0F;
      }

      double var4 = MathHelper.lerp((double)var3, var1.lastRenderX, var1.getX()) - var2.x;
      double var6 = MathHelper.lerp((double)var3, var1.lastRenderY, var1.getY()) - var2.y;
      double var8 = MathHelper.lerp((double)var3, var1.lastRenderZ, var1.getZ()) - var2.z;
      Vec3d var10 = field_4219.getEntityRenderDispatcher().getRenderer(var1).getPositionOffset(var1, var3);
      double var11 = var4 + var10.getX();
      double var13 = var6 + var10.getY();
      double var15 = var8 + var10.getZ();
      var0.push();
      var0.translate(var11, var13, var15);
      Class_0597 var17 = method_16(var1);
      if (var17 != null) {
         var17.method_2(var1, Class_0838.method_776(), var0);
      }

      var0.pop();
   }

   public static <E extends Entity> Class_0597<E> method_16(E var0) {
      return (Class_0597<E>)field_1529.getOrDefault(var0.getType(), var0 instanceof LivingEntity ? field_1530 : null);
   }

   public static void method_2(Class_0661 var0, ModelPart var1) {
      if (var1.visible) {
         var0.method_667().push();
         var1.rotate(var0.method_667());

         for (Cuboid var3 : var1.cuboids) {
            method_2(var0, var3);
         }

         for (ModelPart var5 : var1.children.values()) {
            method_2(var0, var5);
         }

         var0.method_667().pop();
      }
   }

   public static void method_2(Class_0661 var0, Cuboid var1) {
      for (Quad var5 : var1.sides) {
         field_1525.set(var5.vertices[0].pos.x / 16.0F, var5.vertices[0].pos.y / 16.0F, var5.vertices[0].pos.z / 16.0F, 1.0F);
         field_1526.set(var5.vertices[1].pos.x / 16.0F, var5.vertices[1].pos.y / 16.0F, var5.vertices[1].pos.z / 16.0F, 1.0F);
         field_1527.set(var5.vertices[2].pos.x / 16.0F, var5.vertices[2].pos.y / 16.0F, var5.vertices[2].pos.z / 16.0F, 1.0F);
         field_1528.set(var5.vertices[3].pos.x / 16.0F, var5.vertices[3].pos.y / 16.0F, var5.vertices[3].pos.z / 16.0F, 1.0F);
         if (field_1534.getAlpha() != 0) {
            if (!field_1531.method_435()) {
               field_1531.method_9(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
            }

            field_1531.vertex(var0.method_667().peek().getPositionMatrix(), field_1525.x, field_1525.y, field_1525.z).color(field_1534.hashCode());
            field_1531.vertex(var0.method_667().peek().getPositionMatrix(), field_1526.x, field_1526.y, field_1526.z).color(field_1534.hashCode());
            field_1531.vertex(var0.method_667().peek().getPositionMatrix(), field_1527.x, field_1527.y, field_1527.z).color(field_1534.hashCode());
            field_1531.vertex(var0.method_667().peek().getPositionMatrix(), field_1528.x, field_1528.y, field_1528.z).color(field_1534.hashCode());
         }

         if (field_1533.getAlpha() != 0) {
            if (!field_1532.method_435()) {
               field_1532.method_9(DrawMode.LINES, VertexFormats.LINES);
            }

            method_2(var0, field_1525.x, field_1525.y, field_1525.z, field_1526.x, field_1526.y, field_1526.z);
            method_2(var0, field_1526.x, field_1526.y, field_1526.z, field_1527.x, field_1527.y, field_1527.z);
            method_2(var0, field_1527.x, field_1527.y, field_1527.z, field_1528.x, field_1528.y, field_1528.z);
            method_2(var0, field_1525.x, field_1525.y, field_1525.z, field_1525.x, field_1525.y, field_1525.z);
         }
      }
   }

   public static void method_2(Class_0661 var0, float var1, float var2, float var3, float var4, float var5, float var6) {
      field_1532.vertex(var0.method_667().peek().getPositionMatrix(), var1, var2, var3)
         .color(field_1533.hashCode())
         .normal(var0.method_667().peek(), var4 - var1, var5 - var2, var6 - var3);
      field_1532.vertex(var0.method_667().peek().getPositionMatrix(), var4, var5, var6)
         .color(field_1533.hashCode())
         .normal(var0.method_667().peek(), var4 - var1, var5 - var2, var6 - var3);
   }

   public static void method_5(double var0) {
      field_1536 = var0;
   }

   static {
      field_4220.method_5(Class_0482.class);
      field_1529.put(EntityType.END_CRYSTAL, new Class_0275());
      field_1529.put(EntityType.BOAT, new Class_1234());
      field_1529.put(EntityType.CHEST_BOAT, new Class_1234());
      field_1529.put(EntityType.LLAMA, new Class_0475());
      field_1529.put(EntityType.RABBIT, new Class_1361());
      field_1529.put(EntityType.ENDER_DRAGON, new Class_0192());
   }
}
