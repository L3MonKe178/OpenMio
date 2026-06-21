package me.mioclient.event;

import me.mioclient.internal.Event;

public final class Event_60 extends Event {
   public final int field_4205;

   public Event_60(int var1) {
      super();
      this.field_4205 = var1;
   }

   public String method_1166() {
      return Character.toString(this.field_4205);
   }

   public char method_1167() {
      return this.method_1166().charAt(0);
   }
}
