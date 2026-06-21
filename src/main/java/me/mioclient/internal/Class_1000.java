package me.mioclient.internal;

import me.mioclient.api.Class_1171;
import me.mioclient.api.MioAPI;
import net.minecraft.client.gl.Framebuffer;

public final class Class_1000 implements MioAPI {
   public static Framebuffer field_2895;

   public Class_1000() {
      super();
      throw new AssertionError();
   }

   public static void method_2(Class_0726 var0, boolean var1, Runnable var2) {
      method_2(var0, var1);
      var2.run();
      field_4219.getBufferBuilders().getEntityVertexConsumers().draw();
      method_31(var1);
   }

   public static void method_2(Class_0726 var0, boolean var1) {
      if (var1) {
         Class_1355.method_528();
      }

      field_2895 = ((Class_1171)field_4219.worldRenderer).getFramebuffer();
      ((Class_1171)field_4219.worldRenderer).setFramebuffer(var0.field_2302);
      field_4219.getFramebuffer().endWrite();
      Class_1355.field_4422 = true;
   }

   public static void method_31(boolean var0) {
      Class_1355.field_4422 = false;
      ((Class_1171)field_4219.worldRenderer).setFramebuffer(field_2895);
      field_4219.getFramebuffer().beginWrite(false);
      if (var0) {
         Class_1355.method_434();
      }
   }
}
