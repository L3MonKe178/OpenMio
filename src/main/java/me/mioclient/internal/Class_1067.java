package me.mioclient.internal;

import me.mioclient.api.Class_1309;
import net.fabricmc.loader.api.FabricLoader;

public class Class_1067 implements Class_1309 {
   public static double field_3274;
   public static boolean field_3275 = FabricLoader.getInstance().isModLoaded("advanced-ui-scale");

   public Class_1067() {
      super();
   }

   public static void method_669() {
      method_38(Float.intBitsToFloat(1065353216));
   }

   public static void method_38(float var0) {
      if (!method_951()) {
         field_3274 = field_4219.getWindow().getScaleFactor();
         field_4219.getWindow().setScaleFactor((double)(method_950() * var0));
      }
   }

   public static void method_159() {
      if (!method_951()) {
         if (field_3274 == Double.longBitsToDouble(-4616189618054758400L)) {
            throw new UnsupportedOperationException();
         } else {
            field_4219.getWindow().setScaleFactor(field_3274);
            field_3274 = Double.longBitsToDouble(-4616189618054758400L);
         }
      }
   }

   public static float method_950() {
      return (float)field_4219.getWindow().calculateScaleFactor(2, field_4219.forcesUnicodeFont());
   }

   public static double method_78(float var0) {
      return method_951() ? Double.longBitsToDouble(4607182418800017408L) : (double)method_950() / field_4219.getWindow().getScaleFactor() * (double)var0;
   }

   public static boolean method_951() {
      return field_3275;
   }
}
