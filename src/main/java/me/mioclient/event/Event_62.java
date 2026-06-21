package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.screen.slot.Slot;

public class Event_62 extends Event {
   public final Slot field_3478;

   public Event_62(Slot var1) {
      super();
      this.field_3478 = var1;
   }

   public Slot method_1006() {
      return this.field_3478;
   }
}
