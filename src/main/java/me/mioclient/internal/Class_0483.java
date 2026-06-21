package me.mioclient.internal;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class Class_0483 extends Class_0996 {
   public static final Class_0483 field_1537 = new Class_0483();

   public Class_0483() {
      super();
   }

   public static Class_0483 method_2(Entity var0, MatrixStack var1, VertexConsumerProvider var2) {
      field_1537.field_581 = var0;
      field_1537.field_2127 = var1;
      field_1537.field_3084 = var2;
      field_1537.method_616();
      return field_1537;
   }
}
