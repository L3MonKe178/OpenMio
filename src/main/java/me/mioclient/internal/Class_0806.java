package me.mioclient.internal;

import me.mioclient.api.Class_1309;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_9;
import me.mioclient.module.movement.SpeedModule;

public abstract class Class_0806 implements Class_1309 {
   public final SpeedModule field_2537;
   public double field_2538 = Double.longBitsToDouble(4598847156609680094L);
   public double field_1570;
   public double field_1419;
   public int field_1571;

   public Class_0806(SpeedModule var1) {
      super();
      this.field_2538 = Double.longBitsToDouble(4598847156609680094L);
      this.field_2537 = var1;
   }

   public abstract void method_9(Event_9 var1);

   public void method_2(Event_19 var1) {
      if (var1.method_213() != PreType.POST) {
         if (!Class_0464.method_363()) {
            this.field_1571 = 4;
            this.field_1570 = 0.0;
         }

         double var2 = field_4219.player.getX() - field_4219.player.prevX;
         double var4 = field_4219.player.getZ() - field_4219.player.prevZ;
         this.field_1419 = Math.sqrt(var2 * var2 + var4 * var4);
      }
   }

   public void method_738() {
   }

   public void onEnable() {
   }

   public boolean method_739() {
      return !field_4219.world.isSpaceEmpty(field_4219.player.getBoundingBox().offset(0.0, field_4219.player.getVelocity().y, 0.0));
   }
}
