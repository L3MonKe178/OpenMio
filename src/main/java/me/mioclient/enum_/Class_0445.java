package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_9;

public enum Class_0445 implements Nameable {
   NONE("None"),
   PLAIN("Plain") {
      public long field_677;

      @Override
      public void method_2(Event_19 var1) {
         if (!MioAPI.field_4219.player.isOnGround()) {
            if (System.currentTimeMillis() - this.field_677 >= 500L && !MioAPI.field_4219.player.isOnGround()) {
               this.field_677 = System.currentTimeMillis();
               var1.setY(var1.method_395() - 0.04);
            }
         }
      }
   },
   ALTERNATIVE("Alternative") {
      public boolean field_535;

      @Override
      public void method_2(Event_9 var1) {
         if (!MioAPI.field_4219.player.isOnGround()) {
            double var2 = 0.04;
            if (this.field_535) {
               var2 = -var2;
            }

            var1.setY(var1.method_395() + var2);
            MioAPI.field_4219.player.setVelocity(MioAPI.field_4219.player.getVelocity().add(0.0, var2, 0.0));
            this.field_535 = !this.field_535;
         }
      }
   };

   public final String field_1428;

    Class_0445(String var3) {
      this.field_1428 = var3;
   }

   @Override
   public String getName() {
      return this.field_1428;
   }

   public void method_2(Event_9 var1) {
   }

   public void method_2(Event_19 var1) {
   }
}
