package me.mioclient.event;

import me.mioclient.internal.Class_0978;
import net.minecraft.client.util.math.MatrixStack;

public class Event_12 extends Class_0978 {
   public static final Event_12 field_3284 = new Event_12();

   public Event_12() {
      super();
   }

   public static Event_12 method_5(MatrixStack var0, float var1) {
      field_3284.field_2997 = var0;
      field_3284.field_2998 = var1;
      field_3284.method_616();
      return field_3284;
   }
}
