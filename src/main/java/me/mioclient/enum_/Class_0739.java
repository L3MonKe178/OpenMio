package me.mioclient.enum_;

import me.mioclient.api.Nameable;
import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_9;
import me.mioclient.internal.Class_0464;
import me.mioclient.module.movement.FlightModule;
import net.minecraft.util.math.Direction.Axis;

public enum Class_0739 implements Nameable {
   VANILLA("Vanilla") {
      @Override
      public void method_2(FlightModule var1, Event_9 var2) {
         MioAPI.field_4219.player.getAbilities().flying = true;
         MioAPI.field_4219.player.getAbilities().setFlySpeed((float)(var1.method_874() * 0.05F));
      }
   },
   STATIC("Static") {
      public int field_31;

      @Override
      public void method_2(FlightModule var1, Event_9 var2) {
         double var3 = var1.field_3489.getValue() ? (double)var1.field_3490.getValue().floatValue() : 0.0;
         boolean var5 = var1.field_3492.getValue() == Class_0445.ALTERNATIVE;
         if (MioAPI.field_4219.player.input.jumping) {
            this.field_31++;
            if (var5) {
               if (this.field_31 >= 18) {
                  var3 = -0.1;
               }

               if (this.field_31 >= 21) {
                  this.field_31 = 0;
               }
            }

            var2.setY(var3);
            MioAPI.field_4219.player.setVelocity(MioAPI.field_4219.player.getVelocity().withAxis(Axis.Y, var3));
         } else if (MioAPI.field_4219.player.input.sneaking) {
            var2.setY(-var3);
            MioAPI.field_4219.player.setVelocity(MioAPI.field_4219.player.getVelocity().withAxis(Axis.Y, -var3));
         } else {
            var2.setY(0.0);
            MioAPI.field_4219.player.setVelocity(MioAPI.field_4219.player.getVelocity().withAxis(Axis.Y, 0.0));
            if (!MioAPI.field_4219.player.verticalCollision && var1.field_3491.getValue() != 0.0F) {
               MioAPI.field_4219.player.setVelocity(MioAPI.field_4219.player.getVelocity().add(0.0, (double)(-var1.field_3491.getValue()), 0.0));
               var2.setY(MioAPI.field_4219.player.getVelocity().y);
            }
         }

         double[] var6 = Class_0464.method_2(var2, var1.method_874());
         MioAPI.field_4219.player.setVelocity(var6[0], MioAPI.field_4219.player.getVelocity().y, var6[1]);
      }
   },
   JETPACK("JetPack") {
      @Override
      public void method_2(FlightModule var1, Event_19 var2) {
         if (MioAPI.field_4219.options.jumpKey.isPressed()) {
            MioAPI.field_4219.player.setVelocity(MioAPI.field_4219.player.getVelocity().add(0.0, var1.method_874() * 0.1, 0.0));
         }
      }
   },
   SOURCE("Source") {
      @Override
      public void method_2(FlightModule var1, Event_9 var2) {
         double var3 = var1.method_874();
         Class_0464.method_2(var2, var3);
         if (Class_0464.method_363()) {
            var2.setY(
               (double)(-Math.signum(MioAPI.field_4219.player.input.movementForward))
                  * Math.sin(Math.toRadians((double)MioAPI.field_4219.player.getPitch()))
                  * var3
            );
         } else {
            var2.setY(0.0);
         }
      }
   };

   public final String field_2357;

    Class_0739(String var3) {
      this.field_2357 = var3;
   }

   @Override
   public String getName() {
      return this.field_2357;
   }

   public void method_2(FlightModule var1, Event_19 var2) {
   }

   public void method_2(FlightModule var1, Event_9 var2) {
   }
}
