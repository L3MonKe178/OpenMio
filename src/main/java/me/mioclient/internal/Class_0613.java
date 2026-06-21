package me.mioclient.internal;

import java.util.concurrent.TimeUnit;
import me.mioclient.Hub;
import me.mioclient.enum_.Class_0323;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_16;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.module.abstract_.AbstractModule_28;
import me.mioclient.module.movement.ElytraFlyModule;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Class_0613 extends Class_0716 {
   public static final AbstractModule_28 field_1894 = Hub.field_2595.method_78(AbstractModule_28.class);
   public final Class_0242 field_1895 = new Class_0242();
   public boolean field_1896;
   public boolean field_1897;
   public float field_1761;

   public Class_0613(ElytraFlyModule var1) {
      super(var1);
   }

   @Override
   public void onEnable() {
      this.field_1896 = true;
      this.field_1761 = this.field_2275.field_4363.getValue();
   }

   @Override
   public void method_2(Event_1 var1) {
   }

   @Override
   public void method_2(Event_9 var1) {
      float var2 = this.field_2275.field_4356.getValue();
      if (var2 > Float.intBitsToFloat(1128792064) && this.method_625()) {
         var2 = Float.intBitsToFloat(1128792064);
      }

      if (!this.field_2275.field_4356.method_84() || this.method_625()) {
         Vec3d var3 = var1.method_1066();
         double var4 = Math.hypot(var3.getX(), var3.getZ());
         if (!(var4 * Double.longBitsToDouble(4626322717216342016L) * Double.longBitsToDouble(4615288898129284301L) <= (double)var2)) {
            Vec3d var6 = var1.method_1066().normalize();
            var6 = var6.multiply((double)var2 / Double.longBitsToDouble(4615288898129284301L) / Double.longBitsToDouble(4626322717216342016L));
            var1.method_7(var6.getX(), var6.getZ());
         }
      }
   }

   @Override
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof HealthUpdateS2CPacket var2 && var2.getHealth() + var2.getSaturation() < Class_0396.method_76()) {
         this.onDisable();
         this.onEnable();
      }
   }

   @Override
   public void method_2(Event_10 var1) {
   }

   @Override
   public void method_9(Event_19 var1) {
   }

   @Override
   public void method_2(Event_17 var1) {
      this.field_1897 = false;
      if (!Class_0382.method_7(field_4219.player)) {
         if (!field_1894.method_1052() || !field_4219.player.isUsingItem()) {
            boolean var2 = field_4219.player.input.jumping;
            if (this.field_2275.field_4350.getValue() == Class_0323.STRICT) {
               var2 = !field_4219.player.isOnGround() && field_4219.player.getVelocity().getY() < Double.longBitsToDouble(4591870180066957722L);
            } else {
               Hub.field_2596.method_38(this.field_2275);
            }

            if (field_4219.player.isFallFlying()) {
               this.field_1895.reset();
            }

            if ((var2 || !this.field_1895.method_9(1500L))
               && !field_4219.player.isFallFlying()
               && !Class_0396.method_2(field_4219.player)
               && this.field_2275.method_558()) {
               Class_1261.method_1099();
               field_4219.player.startFallFlying();
            }

            if (this.field_2275.method_428()) {
               if (this.field_2275.field_4360.getValue()) {
                  this.method_626();
               }

               float var3 = (float)Math.toRadians((double)field_4219.player.getYaw());
               float var4 = this.field_2275.field_4354.getValue() * Float.intBitsToFloat(1036831949);
               if (this.field_2275.field_4354.method_84() && field_1894.method_1052()) {
                  var4 = field_4219.player.getPitch() > 0.0F
                     ? Float.intBitsToFloat(1020054733)
                     : Float.intBitsToFloat(1016296636) + (float)this.field_2275.field_4355.getValue().intValue() * Float.intBitsToFloat(981668463);
                  if (field_4219.player.getPitch() <= 0.0F
                     && !Hub.field_2602.method_984().method_2(Double.longBitsToDouble(4621819117588971520L), TimeUnit.SECONDS)) {
                     var4 = Float.intBitsToFloat(1006834287);
                  }
               }

               if (this.method_625()) {
                  var4 = Float.intBitsToFloat(1077936128);
               }

               this.method_624();
               if (!this.field_1897) {
                  if (this.field_2275.field_4358.getValue() && field_4219.player.isInLava()) {
                     var4 = Float.intBitsToFloat(1045220557) * this.field_2275.field_4359.getValue();
                  }

                  if (this.field_2275.field_4351.getValue()
                     ? !(field_4219.player.getPitch() > 0.0F)
                     : !field_4219.options.forwardKey.isPressed() && !this.field_2275.field_4352.getValue()) {
                     if (field_4219.options.backKey.isPressed()) {
                        field_4219.player.addVelocity((double)(MathHelper.sin(var3) * var4), 0.0, (double)(MathHelper.cos(var3) * -var4));
                     }
                  } else {
                     field_4219.player.addVelocity((double)(MathHelper.sin(var3) * -var4), 0.0, (double)(MathHelper.cos(var3) * var4));
                  }
               }
            }
         }
      }
   }

   @Override
   public void method_9(Event_39 var1) {
   }

   @Override
   public void method_5(Event_16 var1) {
      if (this.field_2275.field_4357.getValue() && field_4219.player.isFallFlying()) {
         var1.method_276().jumping = true;
      }
   }

   public void method_624() {
      if (field_4219.player.getPitch() < 0.0F && this.field_2275.field_4353.getValue()) {
         int var1 = field_4219.player.getFallFlyingTicks();
         if (var1 % 6 > 2 || var1 >= 1 && var1 < 10) {
            this.field_1897 = true;
            return;
         }

         Hub.field_2598.method_2(new float[]{field_4219.player.getYaw(), 0.0F}, 10000, true);
         Hub.field_2598.method_511();
      }
   }

   public boolean method_625() {
      return this.field_2275.field_4353.getValue() && field_4219.player.getFallFlyingTicks() <= 5;
   }

   public void method_626() {
      float var1 = this.field_2275.field_4363.getValue();
      if (field_4219.player.getY() >= (double)(this.field_2275.field_4361.getValue() + this.field_2275.field_4362.getValue())) {
         this.field_1896 = true;
      }

      if (field_4219.player.getY() <= (double)this.field_2275.field_4361.getValue().intValue() && this.field_1896) {
         this.field_1896 = false;
      }

      if (this.field_1896) {
         this.field_1761 = this.field_1761 + Float.intBitsToFloat(1082130432);
      } else {
         this.field_1761 = this.field_1761 - Float.intBitsToFloat(1082130432);
      }

      this.field_1761 = MathHelper.clamp(this.field_1761, -var1, Math.max(var1, field_4219.player.getPitch()));
   }

   public float method_501() {
      return this.field_1761;
   }
}
