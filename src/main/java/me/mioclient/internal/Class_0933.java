package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0845;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.mixin.ducks.DuckPlayerMoveC2SPacket;
import me.mioclient.module.abstract_.AbstractModule_6;
import me.mioclient.module.movement.ElytraFlyModule;
import me.mioclient.module.movement.FireworksModule;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;

public class Class_0933 extends Class_0716 {
   public static final AbstractModule_6 field_2917 = Hub.field_2595.method_78(AbstractModule_6.class);
   public static FireworksModule fireworks = Hub.field_2595.method_78(FireworksModule.class);
   public Vec3d field_2918 = Vec3d.ZERO;
   public int field_31;
   public double field_1570;
   public long field_303;
   public long field_2919;

   public Class_0933(ElytraFlyModule var1) {
      super(var1);
   }

   @Override
   public void onEnable() {
      this.field_1570 = 0.0;
      this.field_2919 = this.field_303 = System.currentTimeMillis();
   }

   @Override
   public void method_2(Event_1 var1) {
      if (!field_4219.player.isFallFlying()) {
         this.field_2919 = System.currentTimeMillis();
      }

      if (!field_4219.player.input.jumping && !field_4219.player.input.sneaking) {
         this.field_2919 = System.currentTimeMillis();
      }
   }

   @Override
   public void method_2(Event_9 var1) {
      if (!this.method_557()) {
         if (!field_4219.player.isFallFlying() || this.field_2275.method_1182()) {
            this.field_303 = System.currentTimeMillis();
         } else if (this.method_847() && this.field_2275.method_558()) {
            if (!Class_0464.method_363()) {
               this.field_303 = System.currentTimeMillis();
            }

            float var2 = this.field_2275.field_4366.getValue();
            if (this.field_2275.field_4374.getValue()) {
               this.field_1570 = (double)(
                  (float)Class_0464.method_2(
                     (double)this.field_2275.field_4364.getValue().floatValue(),
                     (double)this.field_2275.field_4376.getValue().floatValue(),
                     (double)this.field_2275.field_4377.getValue().floatValue(),
                     this.field_303
                  )
               );
               var2 = (float)Class_0464.method_2(
                  (double)var2,
                  (double)this.field_2275.field_4375.getValue().floatValue(),
                  (double)this.field_2275.field_4377.getValue().floatValue(),
                  this.field_2919
               );
            } else {
               this.field_1570 = (double)this.field_2275.field_4364.getValue().floatValue();
            }

            this.field_1570 = this.field_1570 + (double)fireworks.method_100(false);
            if (this.field_31 == 0) {
               if (field_4219.player.input.jumping
                  && this.field_2275.field_4365.getValue() == Class_0845.STRICT
                  && this.field_1570 >= Double.longBitsToDouble(4611686018427387904L)) {
                  this.field_1570 = Double.longBitsToDouble(4611686018427387904L);
               }

               double[] var3 = Class_0464.method_2(var1, this.field_1570);
               field_4219.player.setVelocity(var3[0], field_4219.player.getVelocity().y, var3[1]);
               this.field_2918 = field_4219.player.getVelocity();
               if (this.field_2275.field_4368.getValue() && field_4219.player.age % 4 == 0) {
                  var1.setY(var1.method_395() - Double.longBitsToDouble(4547007122018943789L));
                  field_4219.player.setVelocity(field_4219.player.getVelocity().add(0.0, Double.longBitsToDouble(-4676364914835832019L), 0.0));
               }
            }

            if (field_4219.player.input.jumping && field_4219.player.isFallFlying()) {
               if (this.field_2275.field_4365.getValue() == Class_0845.STRICT) {
                  this.field_31++;
               }

               if (this.field_2275.field_4365.getValue() == Class_0845.PLAIN) {
                  var1.setY((double)var2);
                  field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, (double)var2));
               } else if (this.field_2275.field_4365.getValue() == Class_0845.STRICT && Class_0464.method_363()) {
                  this.method_845();
               }
            } else if (field_4219.player.input.sneaking) {
               this.field_31 = 0;
               var1.setY((double)(-var2));
               field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, (double)(-var2)));
            } else {
               this.field_31 = 0;
               var1.setY(0.0);
               field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, 0.0));
               if (!field_4219.player.verticalCollision && this.field_2275.field_4369.getValue() != 0.0F) {
                  field_4219.player.setVelocity(field_4219.player.getVelocity().add(0.0, (double)(-this.field_2275.field_4369.getValue()), 0.0));
                  var1.setY(field_4219.player.getVelocity().y);
               }
            }
         }
      }
   }

   @Override
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket) {
         this.field_303 = System.currentTimeMillis();
      }
   }

   @Override
   public void method_2(Event_10 var1) {
      if (!this.method_557()) {
         if (var1.method_127() instanceof PlayerMoveC2SPacket var2 && field_4219.player.isFallFlying()) {
            if (this.field_2275.method_1182() || !this.method_847() || !this.field_2275.method_558()) {
               return;
            }

            if (this.method_846()) {
               ((DuckPlayerMoveC2SPacket)var2).setPitch(this.method_501());
               return;
            }

            if (!Class_0464.method_363()) {
               return;
            }

            if (this.field_2275.field_4370.getValue()) {
               ((DuckPlayerMoveC2SPacket)var2).setPitch(this.field_2275.field_4371.getValue());
            }

            if (this.field_31 > 0) {
               ((DuckPlayerMoveC2SPacket)var2).setPitch(-this.field_2275.field_4367.getValue());
            }
         }
      }
   }

   @Override
   public void method_9(Event_19 var1) {
      if (field_4219.player.isFallFlying()
         && !this.field_2275.method_1182()
         && this.method_847()
         && this.field_2275.method_558()
         && var1.method_213() == PreType.PRE) {
         if (!this.method_557()) {
            if (this.method_846()) {
               var1.setPitch(this.method_501());
            } else if (Class_0464.method_363()) {
               var1.setYaw(this.method_500());
               if (this.field_2275.field_4370.getValue()) {
                  var1.setPitch(this.field_2275.field_4371.getValue());
               }

               if (this.field_31 > 0) {
                  var1.setPitch(-this.field_2275.field_4367.getValue());
               }
            }
         }
      }
   }

   @Override
   public void method_2(Event_17 var1) {
   }

   @Override
   public void method_9(Event_39 var1) {
      if (this.field_2275.method_558() && field_4219.player.isFallFlying() && !this.field_2275.method_1182() && this.method_847()) {
         if (!this.method_557()) {
            if (this.method_846()) {
               var1.setPitch(this.method_501());
            }

            if (Class_0464.method_363()) {
               var1.setYaw(this.method_500());
            }
         }
      }
   }

   @Override
   public void method_5(Event_16 var1) {
      if (var1.method_276() != null && field_4219.player.input != null) {
         if (!this.method_557() && this.field_2275.method_1182()) {
            var1.method_276().jumping = true;
            var1.method_276().pressingForward = true;
            var1.method_276().movementForward = var1.method_278() ? var1.method_277() : Float.intBitsToFloat(1065353216);
         }
      }
   }

   public void method_845() {
      double var1 = (double)Class_0930.method_7(-this.field_2275.field_4367.getValue());
      Vec3d var3 = this.field_2918;
      Vec3d var4 = Class_0930.method_9(-this.field_2275.field_4367.getValue(), field_4219.player.getYaw());
      double var5 = var3.horizontalLength();
      double var7 = var4.horizontalLength();
      double var9 = var4.length();
      double var11 = Math.cos(var1);
      var11 = var11 * var11 * Math.min(Double.longBitsToDouble(4607182418800017408L), var9 / Double.longBitsToDouble(4600877379321698714L));
      var3 = var3.add(
         0.0,
         Double.longBitsToDouble(4590429028186199163L)
            * (Double.longBitsToDouble(-4616189618054758400L) + var11 * Double.longBitsToDouble(4604930618986332160L)),
         0.0
      );
      if (var1 < 0.0 && var7 > 0.0) {
         double var13 = var5 * -Math.sin(var1) * Double.longBitsToDouble(4585925428558828667L);
         var3 = var3.add(-var4.x * var13 / var7, var13 * Double.longBitsToDouble(4614388178203810202L), -var4.z * var13 / var7);
      }

      field_4219.player
         .setVelocity(
            var3.multiply(
               Double.longBitsToDouble(4607092346807469998L), Double.longBitsToDouble(4607002274814922588L), Double.longBitsToDouble(4607092346807469998L)
            )
         );
      this.field_2918 = field_4219.player.getVelocity();
   }

   public float method_500() {
      double[] var1 = Class_0464.method_2(field_4219.player.getYaw(), field_4219.player.input, Double.longBitsToDouble(4607182418800017408L));
      return (float)(Math.toDegrees(Math.atan2(var1[1], var1[0])) - (double)Class_0245.field_685);
   }

   public float method_501() {
      float var1 = field_4219.player.getPitch();
      if (this.method_846()) {
         var1 = (float)Class_0245.field_685;
         if (Class_0464.method_363()) {
            var1 = (float)Class_0245.field_684;
         }

         if (field_4219.player.input.jumping) {
            var1 = -var1;
         }
      }

      return var1;
   }

   public boolean method_846() {
      return field_4219.player.input.jumping
         || field_4219.player.input.sneaking && this.field_2275.field_4365.getValue() == Class_0845.PLAIN && Class_0485.method_513();
   }

   public boolean method_847() {
      if (!field_4219.player.isFallFlying() || !this.field_2275.method_558() || !fireworks.field_2681.getValue() || !fireworks.isToggled()) {
         return true;
      } else {
         return !fireworks.field_2691.method_9(500L) ? true : fireworks.method_784();
      }
   }

   public boolean method_557() {
      return field_2917 == null ? false : field_2917.method_1088();
   }
}
