package me.mioclient.internal;

import me.mioclient.mixin.ducks.DuckVec3d;
import net.minecraft.util.math.Vec3d;

public class Class_1334 {
   public Class_1334() {
      super();
      throw new AssertionError();
   }

   public static void method_2(Vec3d var0, double var1, double var3, double var5) {
      method_2((DuckVec3d)var0, var1, var3, var5);
   }

   public static void method_2(DuckVec3d var0, double var1, double var3, double var5) {
      var0.setX(var1);
      var0.setY(var3);
      var0.setZ(var5);
   }

   public static float method_5(Vec3d var0, Vec3d var1) {
      return method_2(var0.x, var0.z, var1.x, var1.z);
   }

   public static float method_2(double var0, double var2, double var4, double var6) {
      double var8 = var0 - var4;
      double var10 = var2 - var6;
      return (float)Math.hypot(var8, var10);
   }
}
