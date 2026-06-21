package me.mioclient.event;

import me.mioclient.api.MioAPI;
import me.mioclient.internal.Event;

public final class Event_7 extends Event implements MioAPI {
   public Event_7() {
      super();
   }

   public float method_500() {
      return field_4219.player.getYaw();
   }

   public float method_501() {
      return field_4219.player.getPitch();
   }
}
