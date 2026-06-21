package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class Event_26 extends Event {
   public static final Event_26 field_3861 = new Event_26();
   public MatrixStack field_2997;
   public DrawContext field_2999;
   public float field_2998;

   public Event_26() {
      super();
   }

   public static Event_26 method_2(MatrixStack var0, DrawContext var1, float var2) {
      field_3861.field_2997 = var0;
      field_3861.field_2998 = var2;
      field_3861.field_2999 = var1;
      field_3861.method_616();
      return field_3861;
   }

   public MatrixStack method_10() {
      return this.field_2997;
   }

   public float method_880() {
      return this.field_2998;
   }

   public DrawContext method_881() {
      return this.field_2999;
   }
}
