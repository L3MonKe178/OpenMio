package me.mioclient.event;

import me.mioclient.internal.Event;
import net.minecraft.world.chunk.WorldChunk;

public class Event_5 extends Event {
   public final WorldChunk field_3541;

   public Event_5(WorldChunk var1) {
      super();
      this.field_3541 = var1;
   }

   public WorldChunk method_1021() {
      return this.field_3541;
   }
}
