package me.mioclient.internal;

import me.mioclient.event.Event_9;
import me.mioclient.module.movement.SpeedModule;

public final class Class_1177 extends Class_0806 {
   public Class_1177(SpeedModule var1) {
      super(var1);
   }

   @Override
   public void method_9(Event_9 var1) {
      if (!this.field_2537.method_526() && !this.field_2537.method_525() && !field_4219.player.isFallFlying() && !this.field_2537.method_678()) {
         Class_0464.method_2(var1, (double)this.field_2537.field_2192.getValue().floatValue());
      }
   }
}
