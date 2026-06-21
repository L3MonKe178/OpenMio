package me.mioclient.internal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import me.mioclient.api.MioAPI;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Class_0930 implements MioAPI {
   public Class_0930() {
      super();
   }

   public static double method_2(double var0, int var2) {
      if (var2 < 0) {
         throw new IllegalArgumentException();
      } else {
         BigDecimal var3 = BigDecimal.valueOf(var0);
         var3 = var3.setScale(var2, RoundingMode.FLOOR);
         return var3.doubleValue();
      }
   }

   public static float method_2(float var0, int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException();
      } else {
         BigDecimal var2 = BigDecimal.valueOf((double)var0);
         var2 = var2.setScale(var1, RoundingMode.FLOOR);
         return var2.floatValue();
      }
   }

   public static float method_2(float var0, float var1) {
      return var0 + new Random().nextFloat() * (var1 - var0);
   }

   public static int method_7(int var0, int var1) {
      return var0 + new Random().nextInt() * (var1 - var0);
   }

   public static double method_2(double var0, double var2, double var4) {
      return var0 + (var2 - var0) * var4;
   }

   public static float method_7(float var0) {
      return var0 * Constants.field_690;
   }

   public static float method_29(float var0) {
      return var0 * Constants.field_691;
   }

   public static Vec3d method_9(float var0, float var1) {
      float var2 = method_7(var0);
      float var3 = method_7(-var1);
      float var4 = MathHelper.cos(var3);
      float var5 = MathHelper.sin(var3);
      float var6 = MathHelper.cos(var2);
      float var7 = MathHelper.sin(var2);
      return new Vec3d((double)(var5 * var6), (double)(-var7), (double)(var4 * var6));
   }

   public static double method_2(Vec3d var0, double var1) {
      return method_2(field_4219.gameRenderer.getCamera().getPos(), var0, var1);
   }

   public static double method_2(Vec3d var0, Vec3d var1, double var2) {
      double var4 = var0.distanceTo(var1);
      double var6 = (double)((Integer)field_4219.options.getFov().getValue()).intValue() / Double.longBitsToDouble(4637440978796412928L);
      if (var6 < Double.longBitsToDouble(4607182418800017408L)) {
         var6 = Double.longBitsToDouble(4607182418800017408L);
      }

      return var4 <= Double.longBitsToDouble(4621819117588971520L) / var2
         ? Math.min(Double.longBitsToDouble(4607182418800017408L), var2) * var6
         : var4 * var2 * Double.longBitsToDouble(4591870180066957722L) * var6;
   }

   public static float method_38(PlayerEntity var0) {
      float[] var1 = RotationManager.method_78(var0.getPos());
      return MathHelper.angleBetween(field_4219.player.getYaw(), var1[0]);
   }

   public static double method_9(double var0, double var2, double var4) {
      double var6 = var0 - var2;
      double var8 = var4 - var0;
      return var8 > var6 ? var2 : var4;
   }

   public static boolean method_17(int var0) {
      return var0 == 0 ? false : new Random().nextInt(100) <= var0;
   }

   public static float method_4(float var0) {
      return (var0 < 0.0F ? var0 + Float.intBitsToFloat(1135869952) : var0) % Float.intBitsToFloat(1135869952);
   }

   public static float method_5(float var0, float var1) {
      return MathHelper.lerp(RenderUtil.method_776(), var0, var1);
   }

   public static double method_2(double var0, double var2) {
      return MathHelper.lerp((double)RenderUtil.method_776(), var0, var2);
   }

   public static double method_35(BlockPos var0) {
      return method_7(var0.toCenterPos());
   }

   public static double method_7(Vec3d var0) {
      return field_4219.gameRenderer.getCamera().getPos().distanceTo(var0);
   }
}
