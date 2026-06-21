package me.mioclient.internal;

import me.mioclient.event.Event_3;
import net.minecraft.client.util.math.MatrixStack;

public class Class_0947 extends Class_0978 {
   public static final Class_0947 field_2951 = new Class_0947();

   public Class_0947() {
      super();
   }

   public static Class_0947 method_2(MatrixStack var0, float var1) {
      field_2951.field_2997 = var0;
      field_2951.field_2998 = var1;
      field_2951.field_2999 = Event_3.field_1557.method_881();
      field_2951.method_616();
      return field_2951;
   }
}
