package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

public class Event_36 extends Event {
   public final BlockHitResult field_1098;
   public final Hand field_1099;

   public Event_36(BlockHitResult var1, Hand var2) {
      super();
      this.field_1098 = var1;
      this.field_1099 = var2;
   }

   public BlockHitResult method_382() {
      return this.field_1098;
   }

   public Hand method_12() {
      return this.field_1099;
   }
}
