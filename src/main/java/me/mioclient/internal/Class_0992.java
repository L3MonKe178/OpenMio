package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.event.Event_9;
import me.mioclient.module.movement.LongJumpModule;
import me.mioclient.module.movement.SpeedModule;

public class Class_0992 extends Class_0806 {
   public static LongJumpModule field_1017 = Hub.field_2595.method_78(LongJumpModule.class);

   public Class_0992(SpeedModule var1) {
      super(var1);
      this.field_1571 = 4;
   }

   @Override
   public void method_9(Event_9 var1) {
      if (!this.field_2537.method_526()
         && !this.field_2537.method_525()
         && !Class_0382.method_427()
         && !field_4219.player.isFallFlying()
         && !field_4219.player.isSpectator()
         && !field_1017.isToggled()) {
         double var6 = var1.method_395();
         if (this.field_2537.field_2195.getValue() && Hub.field_2602.method_984().method_9(250L)) {
            Hub.field_2596.method_2(this.field_2537, Float.intBitsToFloat(1066098124));
         } else if (this.field_2537.field_2195.getValue()) {
            Hub.field_2596.method_38(this.field_2537);
         }

         float var8 = !this.field_2537.field_2198.method_9(1000L) && this.field_2537.field_2193.getValue()
            ? Float.intBitsToFloat(1067030938)
            : Float.intBitsToFloat(1065353216);
         if (this.field_1571 == 1 && Class_0464.method_363()) {
            this.field_1570 = Double.longBitsToDouble(4608758678669597082L)
                  * Class_0464.method_2(
                     false, Double.longBitsToDouble(4598847156609680094L) * (double)this.field_2537.field_2192.getValue().floatValue() * (double)var8
                  )
               - Double.longBitsToDouble(4576918229304087675L);
         } else if (this.field_1571 == 2 && Class_0464.method_363() && field_4219.player.groundCollision) {
            var6 = Double.longBitsToDouble(4601237667291888353L) + Class_0464.method_495();
            this.field_1570 = this.field_1570 * Double.longBitsToDouble(4609591844600660623L);
         } else if (this.field_1571 == 3) {
            this.field_1570 = this.field_1419
               - Double.longBitsToDouble(4604119971289628672L)
                  * (
                     this.field_1419
                        - Class_0464.method_2(
                           true, Double.longBitsToDouble(4598847156609680094L) * (double)this.field_2537.field_2192.getValue().floatValue() * (double)var8
                        )
                  );
         } else {
            if ((this.method_739() || field_4219.player.verticalCollision) && this.field_1571 > 0) {
               this.field_1571 = Class_0464.method_363() ? 1 : 0;
            }

            this.field_1570 = this.field_1419 - this.field_1419 / Double.longBitsToDouble(4639798331726364672L);
         }

         this.field_1570 = Math.max(
            this.field_1570,
            Class_0464.method_2(
               false, Double.longBitsToDouble(4598847156609680094L) * (double)this.field_2537.field_2192.getValue().floatValue() * (double)var8
            )
         );
         double var2;
         double var4;
         if (!Class_0464.method_363()) {
            var2 = 0.0;
            var4 = 0.0;
         } else {
            double[] var9 = Class_0464.method_2(field_4219.player.getYaw(Class_0838.method_776()), field_4219.player.input, this.field_1570);
            var2 = var9[0];
            var4 = var9[1];
         }

         Class_1334.method_2(var1.method_1066(), var2, var6, var4);
         if (Class_0464.method_363()) {
            this.field_1571++;
         }
      }
   }
}
