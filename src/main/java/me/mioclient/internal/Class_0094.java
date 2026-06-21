package me.mioclient.internal;

import me.mioclient.Hub;
import me.mioclient.enum_.Class_0323;
import me.mioclient.enum_.PreType;
import me.mioclient.event.Event_1;
import me.mioclient.event.Event_10;
import me.mioclient.event.Event_17;
import me.mioclient.event.Event_19;
import me.mioclient.event.Event_39;
import me.mioclient.event.Event_4;
import me.mioclient.event.Event_9;
import me.mioclient.mixin.ducks.DuckPlayerMoveC2SPacket;
import me.mioclient.module.movement.ElytraFlyModule;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Direction.Axis;

public class Class_0094 extends Class_0716 {
   public long field_303;
   public boolean field_304;
   public boolean field_305;
   public boolean field_306;

   public Class_0094(ElytraFlyModule var1) {
      super(var1);
   }

   @Override
   public void onEnable() {
      if (field_4219.player.isFallFlying() && !field_4219.player.isOnGround()) {
         this.field_304 = true;
         field_4219.player.stopFallFlying();
      }

      this.field_305 = false;
      this.field_306 = false;
      this.field_303 = System.currentTimeMillis();
   }

   @Override
   public void method_2(Event_1 var1) {
      if (field_4219.player.isOnGround()) {
         this.field_304 = false;
      } else if (field_4219.player.isFallFlying()) {
         this.field_304 = true;
      }

      boolean var2 = field_4219.player.input.jumping;
      if (this.field_2275.field_4350.getValue() == Class_0323.STRICT) {
         var2 = !field_4219.player.isOnGround() && field_4219.player.getVelocity().getY() < Double.longBitsToDouble(4591870180066957722L);
      } else {
         Hub.field_2596.method_38(this.field_2275);
      }

      if (var2 || this.field_305) {
         this.field_2275.method_1099();
         this.field_305 = !field_4219.player.isFallFlying();
      }
   }

   @Override
   public void method_2(Event_9 var1) {
      if (!field_4219.player.isFallFlying() && this.field_304 && !field_4219.player.horizontalCollision && field_4219.player.getVelocity().getY() < 0.0) {
         this.field_305 = true;
      }

      if (field_4219.player.isFallFlying()) {
         if (!Class_0464.method_363()) {
            this.field_303 = System.currentTimeMillis();
         }

         float var2 = this.field_2275.field_4379.getValue();
         float var3 = this.field_2275.field_4378.getValue();
         float var4 = MathHelper.clamp(
            (float)(System.currentTimeMillis() - this.field_303) / Float.intBitsToFloat(1140457472), 0.0F, Float.intBitsToFloat(1065353216)
         );
         float var5 = MathHelper.lerp(var4, var3, var2);
         double[] var6 = Class_0464.method_2(var1, (double)var5);
         field_4219.player.setVelocity(var6[0], 0.0, var6[1]);
         var1.setY(0.0);
         field_4219.player.setVelocity(field_4219.player.getVelocity().withAxis(Axis.Y, 0.0));
      }
   }

   @Override
   public void method_5(Event_4 var1) {
      if (var1.method_127() instanceof PlayerPositionLookS2CPacket && field_4219.player.isFallFlying() && !field_4219.player.isOnGround()) {
         this.field_303 = System.currentTimeMillis();
         field_4219.player.stopFallFlying();
      }
   }

   @Override
   public void method_2(Event_10 var1) {
      if (var1.method_127() instanceof PlayerMoveC2SPacket var2
         && field_4219.player.isFallFlying()
         && !field_4219.player.isOnGround()
         && field_4219.player.getVelocity().getY() < Double.longBitsToDouble(4591870180066957722L)
         && Class_0464.method_363()) {
         ((DuckPlayerMoveC2SPacket)var2).setPitch(Float.intBitsToFloat(-1063256064));
      }
   }

   @Override
   public void method_9(Event_19 var1) {
      if (var1.method_213() == PreType.PRE) {
         boolean var2 = false;
         if (this.field_2275.method_558() && !var1.method_707()) {
            if (!field_4219.player.isFallFlying()) {
               var2 = true;
               this.field_306 = false;
            } else if (!this.field_306) {
               if (!Class_0464.method_363()) {
                  var2 = true;
               } else {
                  this.field_306 = true;
               }
            }
         }

         if (var2) {
            var1.setYaw(var1.method_500() + (float)(field_4219.player.age % 2 == 0 ? -2 : 2));
            var1.setPitch(var1.method_501() + (float)(field_4219.player.age % 2 == 0 ? -2 : 2));
         }
      }
   }

   @Override
   public void method_2(Event_17 var1) {
   }

   @Override
   public void method_9(Event_39 var1) {
   }
}
