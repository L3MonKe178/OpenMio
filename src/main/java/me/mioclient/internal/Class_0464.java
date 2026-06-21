package me.mioclient.internal;

import me.mioclient.api.MioAPI;
import me.mioclient.event.Event_9;
import net.minecraft.client.input.Input;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;

public class Class_0464 implements MioAPI {
   public static float field_1470 = Float.intBitsToFloat(1049827580);
   public static float field_1471 = field_1470 * Float.intBitsToFloat(1045220557);

   public Class_0464() {
      super();
   }

   public static double[] method_2(Event_9 var0, double var1) {
      if (method_363()) {
         double[] var3 = method_2(field_4219.player.getYaw(RenderUtil.method_776()), field_4219.player.input, var1);
         var0.method_7(var3[0], var3[1]);
         return var3;
      } else {
         var0.method_7(0.0, 0.0);
         return new double[]{0.0, 0.0};
      }
   }

   public static double[] method_2(float var0, Input var1, double var2) {
      float var4 = var1.movementForward;
      float var5 = var1.movementSideways;
      float var6 = var0;
      if (var4 != 0.0F) {
         if (var5 > 0.0F) {
            var6 = var0 + (float)(var4 > 0.0F ? -Constants.field_684 : Constants.field_684);
         } else if (var5 < 0.0F) {
            var6 = var0 + (float)(var4 > 0.0F ? Constants.field_684 : -Constants.field_684);
         }

         var5 = 0.0F;
         if (var4 > 0.0F) {
            var4 = Float.intBitsToFloat(1065353216);
         } else if (var4 < 0.0F) {
            var4 = Float.intBitsToFloat(-1082130432);
         }
      }

      double var7 = (double)var4 * var2 * -Math.sin(Math.toRadians((double)var6)) + (double)var5 * var2 * Math.cos(Math.toRadians((double)var6));
      double var9 = (double)var4 * var2 * Math.cos(Math.toRadians((double)var6)) - (double)var5 * var2 * -Math.sin(Math.toRadians((double)var6));
      return new double[]{var7, var9};
   }

   public static double method_2(double var0, double var2, double var4, long var6) {
      if (var2 >= var0) {
         return var0;
      } else {
         double var8 = MathHelper.clamp(
            (double)(System.currentTimeMillis() - var6) / (var4 * Double.longBitsToDouble(4652007308841189376L)),
            0.0,
            Double.longBitsToDouble(4607182418800017408L)
         );
         double var10 = var2 + (var0 - var2) * var8;
         return Math.min(var10, var0);
      }
   }

   public static boolean method_9(PlayerEntity var0) {
      return var0 == null ? false : var0.forwardSpeed != 0.0F || var0.sidewaysSpeed != 0.0F;
   }

   public static boolean method_363() {
      return method_9(field_4219.player);
   }

   public static double method_78(boolean var0) {
      return method_2(var0, (double)field_1470);
   }

   public static double method_2(boolean var0, double var1) {
      double var3 = var1;
      if (field_4219.player.hasStatusEffect(StatusEffects.SPEED)) {
         int var5 = field_4219.player.getStatusEffect(StatusEffects.SPEED).getAmplifier();
         var3 = var1 * (Double.longBitsToDouble(4607182418800017408L) + Double.longBitsToDouble(4596373779694328218L) * (double)(var5 + 1));
      }

      if (var0 && field_4219.player.hasStatusEffect(StatusEffects.SLOWNESS)) {
         int var6 = field_4219.player.getStatusEffect(StatusEffects.SLOWNESS).getAmplifier();
         var3 /= Double.longBitsToDouble(4607182418800017408L) + Double.longBitsToDouble(4596373779694328218L) * (double)(var6 + 1);
      }

      return var3;
   }

   public static double method_495() {
      double var0 = 0.0;
      if (field_4219.player.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
         int var2 = field_4219.player.getStatusEffect(StatusEffects.JUMP_BOOST).getAmplifier();
         var0 += (double)(var2 + 1) * Double.longBitsToDouble(4591870180066957722L);
      }

      return var0;
   }

   public static double method_9(double var0) {
      boolean var2 = field_4219.player.isOnGround() && field_4219.player.horizontalCollision;
      if (!var2) {
         return 0.0;
      } else {
         double var3 = Double.longBitsToDouble(-4616189618054758400L);
         Box var5 = field_4219.player
            .getBoundingBox()
            .offset(0.0, Double.longBitsToDouble(4587366580439587226L), 0.0)
            .expand(Double.longBitsToDouble(4587366580439587226L));
         var5 = var5.withMaxY(var5.maxY + var0);

         for (VoxelShape var7 : field_4219.world.getCollisions(field_4219.player, var5)) {
            for (Box var9 : var7.getBoundingBoxes()) {
               if (var9.maxY > var3) {
                  var3 = var9.maxY;
               }
            }
         }

         var3 -= field_4219.player.getY();
         return var3 > 0.0 && var3 <= var0 ? var3 : 0.0;
      }
   }

   public static boolean method_2(PlayerEntity var0, Vec3d var1) {
      Vec3d var2 = var0.getVelocity();
      if (method_9(var0)) {
         Vec3d var3 = var0.getPos()
            .add(var2.getX() * Double.longBitsToDouble(4666723172467343360L), 0.0, var2.getZ() * Double.longBitsToDouble(4666723172467343360L));
         Vec3d var4 = var0.getPos()
            .add(var2.getX() * Double.longBitsToDouble(-4556648864387432448L), 0.0, var2.getZ() * Double.longBitsToDouble(-4556648864387432448L));
         return var3.squaredDistanceTo(var1) < var4.squaredDistanceTo(var1);
      } else {
         return false;
      }
   }

   public static boolean method_2(Vec3d var0) {
      return method_2(field_4219.player, var0);
   }

   public static float method_496() {
      if (field_4219.player.isFallFlying()) {
         double[] var3 = method_2(field_4219.player.getYaw(), field_4219.player.input, Double.longBitsToDouble(4607182418800017408L));
         return (float)(Math.toDegrees(Math.atan2(var3[1], var3[0])) - (double)Constants.field_685);
      } else {
         float var0 = field_4219.player.getYaw();
         Input var1 = field_4219.player.input;
         boolean var2 = var1.pressingForward != var1.pressingBack;
         if (var1.pressingBack) {
            var0 += Float.intBitsToFloat(1127481344);
         }

         if (var1.pressingRight && !var2) {
            var0 += (float)(Constants.field_685 * (var1.pressingBack ? -1 : 1));
         }

         if (var1.pressingLeft && !var2) {
            var0 -= (float)(Constants.field_685 * (var1.pressingBack ? -1 : 1));
         }

         return var0;
      }
   }
}
