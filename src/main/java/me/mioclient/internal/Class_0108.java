package me.mioclient.internal;

import me.mioclient.event.Event_10;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_43;
import net.minecraft.network.packet.Packet;

public abstract class Class_0108 extends Event {
   public final Packet<?> field_332;

   public Class_0108(Packet<?> var1) {
      super();
      this.field_332 = var1;
   }

   public Packet<?> method_127() {
      return this.field_332;
   }
}
