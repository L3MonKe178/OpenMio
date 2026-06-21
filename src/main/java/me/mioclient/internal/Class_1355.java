package me.mioclient.internal;

import java.util.List;
import me.mioclient.api.Class_1309;
import net.minecraft.client.render.VertexConsumerProvider;

public class Class_1355 implements Class_1309 {
   public static Class_0914 field_4416;
   public static Class_0914 field_4417;
   public static Class_0914 field_4418;
   public static Class_0914 field_4419;
   public static Class_0914 field_4420;
   public static List<Class_0914> field_4421;
   public static boolean field_4422;
   public static boolean field_2003;

   public Class_1355() {
      super();
   }

   public static void init() {
      field_4416 = new Class_1130();
      field_4417 = new Class_1100();
      field_4418 = new Class_0259();
      field_4419 = new Class_0534();
      field_4420 = new Class_1123();
      field_4421 = List.of(field_4416, field_4417, field_4418, field_4419, field_4420);
   }

   public static void method_528() {
      for (Class_0914 var1 : field_4421) {
         var1.method_528();
      }
   }

   public static void method_434() {
      for (Class_0914 var1 : field_4421) {
         var1.method_434();
      }
   }

   public static void method_39(int var0, int var1) {
      if (field_4219 != null) {
         for (Class_0914 var3 : field_4421) {
            var3.method_39(var0, var1);
         }
      }
   }

   public static boolean method_2(VertexConsumerProvider var0) {
      for (Class_0914 var2 : field_4421) {
         if (var2.field_2301 == var0) {
            return true;
         }
      }

      return false;
   }
}
