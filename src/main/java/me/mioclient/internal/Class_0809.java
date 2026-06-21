package me.mioclient.internal;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.util.BufferAllocator;

public class Class_0809 implements VertexConsumer {
   public static final boolean field_2556;
   public final BufferAllocator field_2557;
   public BufferBuilder field_2558;
   public DrawMode field_2559;
   public VertexFormat field_2560;

   public static Class_0809 method_740() {
      return method_34(786432);
   }

   public static Class_0809 method_34(int var0) {
      return field_2556 ? Class_0971.method_374(var0) : new Class_0809(var0);
   }

   public Class_0809(int var1) {
      super();
      this.field_2557 = new BufferAllocator(var1);
   }

   public Class_0809 method_2(DrawMode var1, VertexFormat var2) {
      this.field_2559 = var1;
      this.field_2560 = var2;
      return this;
   }

   public VertexConsumer vertex(float x, float y, float z) {
      return this.method_741().vertex(x, y, z);
   }

   public VertexConsumer color(int red, int green, int blue, int alpha) {
      return this.method_741().color(red, green, blue, alpha);
   }

   public VertexConsumer texture(float u, float v) {
      return this.method_741().texture(u, v);
   }

   public VertexConsumer overlay(int u, int v) {
      return this.method_741().overlay(u, v);
   }

   public VertexConsumer light(int u, int v) {
      return this.method_741().light(u, v);
   }

   public VertexConsumer normal(float x, float y, float z) {
      return this.method_741().normal(x, y, z);
   }

   public boolean method_435() {
      return this.field_2558 != null;
   }

   public BufferBuilder method_9(DrawMode var1, VertexFormat var2) {
      this.field_2558 = new BufferBuilder(this.field_2557, var1, var2);
      return this.field_2558;
   }

   public BufferBuilder method_741() {
      if (this.field_2558 == null) {
         if (this.field_2559 == null || this.field_2560 == null) {
            throw new IllegalArgumentException("Tried to invoke empty not caching buffer");
         }

         this.field_2558 = new BufferBuilder(this.field_2557, this.field_2559, this.field_2560);
      }

      return this.field_2558;
   }

   public BuiltBuffer method_742() {
      if (this.field_2558 == null) {
         throw new IllegalArgumentException("Tried to end empty buffer");
      } else {
         BuiltBuffer var1 = this.field_2558.endNullable();
         this.field_2558 = null;
         return var1;
      }
   }

   public void method_529() {
      if (this.field_2558 != null) {
         method_2(this.field_2558);
         this.field_2558 = null;
      }
   }

   public static void method_2(Class_0809 var0) {
      if (var0.method_435()) {
         method_2(var0.field_2558);
         var0.field_2558 = null;
      }
   }

   public static void method_2(BufferBuilder var0) {
      if (var0 != null) {
         BuiltBuffer var1 = var0.endNullable();
         if (var1 != null) {
            BufferRenderer.drawWithGlobalProgram(var1);
         }
      }
   }

   static {
      boolean var0;
      try {
         Class.forName("net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter");
         var0 = true;
      } catch (ClassNotFoundException var2) {
         var0 = false;
      }

      field_2556 = var0;
   }
}
