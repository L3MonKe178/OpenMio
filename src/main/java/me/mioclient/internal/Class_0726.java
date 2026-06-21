package me.mioclient.internal;

import me.mioclient.api.Class_1309;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.render.OutlineVertexConsumerProvider;
import net.minecraft.entity.Entity;
import org.lwjgl.glfw.GLFW;

public abstract class Class_0726 implements Class_1309 {
   public OutlineVertexConsumerProvider field_2301;
   public Framebuffer field_2302;
   public Class_1119 field_2303;

   public Class_0726() {
      super();
   }

   public void method_251(String var1) {
      this.field_2301 = new OutlineVertexConsumerProvider(field_4219.getBufferBuilders().getEntityVertexConsumers());
      this.field_2302 = new SimpleFramebuffer(
         field_4219.getWindow().getFramebufferWidth(), field_4219.getWindow().getFramebufferHeight(), false, MinecraftClient.IS_SYSTEM_MAC
      );
      this.field_2303 = new Class_1119("/base.vert", new Class_1303().method_2((Object)var1).method_9("/\u0001.frag"));
   }

   public void method_2(String var1, String var2) {
      this.field_2301 = new OutlineVertexConsumerProvider(field_4219.getBufferBuilders().getEntityVertexConsumers());
      this.field_2302 = new SimpleFramebuffer(
         field_4219.getWindow().getFramebufferWidth(), field_4219.getWindow().getFramebufferHeight(), false, MinecraftClient.IS_SYSTEM_MAC
      );
      this.field_2303 = new Class_1119(
         new Class_1303().method_2((Object)var1).method_9("/\u0001.vert"), new Class_1303().method_2((Object)var2).method_9("/\u0001.frag")
      );
   }

   public abstract boolean method_291();

   public abstract boolean method_31(Entity var1);

   public void method_696() {
   }

   public void method_697() {
   }

   public abstract void method_292();

   public void method_528() {
      if (this.method_291()) {
         this.field_2302.clear(MinecraftClient.IS_SYSTEM_MAC);
         field_4219.getFramebuffer().beginWrite(false);
      }
   }

   public void method_39(Runnable var1) {
      if (this.method_291()) {
         this.method_696();
         var1.run();
         this.method_697();
         field_4219.getFramebuffer().beginWrite(false);
         Class_1088.method_38(this.field_2302.getColorAttachment(), 0);
         this.field_2303.method_1004();
         this.field_2303.method_2("u_Size", (double)field_4219.getWindow().getFramebufferWidth(), (double)field_4219.getWindow().getFramebufferHeight());
         this.field_2303.method_2("u_Texture", 0);
         this.field_2303.method_2("u_Time", GLFW.glfwGetTime());
         this.method_292();
         Class_0500.method_529();
      }
   }

   public void method_39(int var1, int var2) {
      if (this.field_2302 != null) {
         this.field_2302.resize(var1, var2, MinecraftClient.IS_SYSTEM_MAC);
      }
   }
}
