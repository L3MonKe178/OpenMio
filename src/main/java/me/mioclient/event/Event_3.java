package me.mioclient.event;

import me.mioclient.internal.Class_0978;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Event_3 extends Class_0978 {
   public static final Event_3 field_1557 = new Event_3();

   public Event_3() {
      super();
   }

   public static Event_3 method_9(MatrixStack var0, float var1) {
      field_1557.field_2997 = var0;
      field_1557.field_2998 = var1;
      field_1557.field_2999 = new DrawContext(MinecraftClient.getInstance(), MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers());
      field_1557.method_616();
      return field_1557;
   }
}
