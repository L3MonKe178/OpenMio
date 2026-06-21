package me.mioclient.event;

import me.mioclient.internal.Class_0605;
import net.minecraft.client.particle.Particle;

public class Event_28 extends Class_0605 {
   public Particle field_2942;

   public Event_28(Particle var1) {
      super();
      this.field_2942 = var1;
   }

   public Particle method_853() {
      return this.field_2942;
   }

   public void method_2(Particle var1) {
      this.field_2942 = var1;
   }
}
