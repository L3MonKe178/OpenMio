package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class Event_27 extends Event {
   public final MatrixStack field_16;
   public final Entity field_17;

   public Event_27(MatrixStack var1, Entity var2) {
      super();
      this.field_16 = var1;
      this.field_17 = var2;
   }

   public MatrixStack method_10() {
      return this.field_16;
   }

   public Entity method_11() {
      return this.field_17;
   }
}
