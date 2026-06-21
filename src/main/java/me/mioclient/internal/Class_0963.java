package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_9;
import me.mioclient.module.movement.SpeedModule;
import net.minecraft.client.network.ClientPlayerEntity;

public final class Class_0963 extends Class_0806 {
   public Class_0963(SpeedModule var1) {
      super(var1);
      this.field_1571 = 2;
   }

   @Override
   public void method_9(Event_9 var1) {
      if (this.method_670()) {
         this.field_2537.reset();
         Class_0464.method_2(var1, Class_0464.method_78(true));
      } else {
         if (this.field_1571 == 4) {
            this.field_2537.reset();
         }

         if (field_4219.player.isOnGround() || this.field_1571 == 3) {
            if (!field_4219.player.horizontalCollision && field_4219.player.forwardSpeed != 0.0F || field_4219.player.sidewaysSpeed != 0.0F) {
               if (this.field_1571 == 2) {
                  this.field_1570 = this.field_1570 * Double.longBitsToDouble(4612021536599627006L);
                  this.field_1571 = 3;
               } else if (this.field_1571 == 3) {
                  this.field_1571 = 2;
                  this.field_1570 = this.field_1419 - Double.longBitsToDouble(4604119971053405471L) * (this.field_1419 - Class_0464.method_78(true));
               }
            }

            this.field_1570 = Math.min(this.field_1570, (double)this.field_2537.field_2192.getValue().floatValue());
            this.field_1570 = Math.max(this.field_1570, Class_0464.method_78(true));
            double[] var2 = Class_0464.method_2(field_4219.player.getYaw(Class_0838.method_776()), field_4219.player.input, this.field_1570);
            if (!field_4219.world.isSpaceEmpty(field_4219.player.getBoundingBox().stretch(var2[0], 0.0, var2[1]))) {
               this.field_2537.reset();
               this.field_1570 = Class_0464.method_78(true);
            }

            Class_0464.method_2(var1, this.field_1570);
         }
      }
   }

   @Override
   public void method_2(Event_19 var1) {
      if (var1.method_213() != PreType.POST) {
         if (!this.method_670()) {
            super.method_2(var1);
            if (this.field_1571 == 3) {
               var1.setY(
                  var1.method_395()
                     + (this.method_739() ? Double.longBitsToDouble(4596373779694328218L) : Double.longBitsToDouble(4600877379321698714L))
                     + Class_0464.method_495()
               );
            }
         }
      }
   }

   public boolean method_670() {
      ClientPlayerEntity var1 = field_4219.player;
      double var2 = var1.getX() - var1.prevX;
      double var4 = var1.getY() - var1.prevY;
      double var6 = var1.getZ() - var1.prevZ;
      if (!field_4219.world.isSpaceEmpty(field_4219.player.getBoundingBox().offset(var2, var4, var6))) {
         return true;
      } else {
         return field_4219.world.isSpaceEmpty(field_4219.player.getBoundingBox().stretch(0.0, Double.longBitsToDouble(-4616189618054758400L), 0.0))
            ? true
            : field_4219.player.horizontalCollision
               || field_4219.player.getY() > Math.floor(field_4219.player.getY())
               || !this.method_864()
               || Hub.field_2612.method_2(field_4219.player, 3).minY < field_4219.player.getY();
      }
   }

   public boolean method_864() {
      return Hub.field_2602.method_984().method_9(500L);
   }
}
