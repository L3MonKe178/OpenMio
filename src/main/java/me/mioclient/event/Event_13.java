package me.mioclient.event;

import me.mioclient.internal.Class_0996;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class Event_13 extends Class_0996 {
   public static final Event_13 field_399 = new Event_13();

   public Event_13() {
      super();
   }

   public static Event_13 method_9(Entity var0, MatrixStack var1, VertexConsumerProvider var2) {
      field_399.field_581 = var0;
      field_399.field_2127 = var1;
      field_399.field_3084 = var2;
      field_399.method_616();
      return field_399;
   }
}
