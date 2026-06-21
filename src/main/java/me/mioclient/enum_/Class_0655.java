package me.mioclient.enum_;

import java.util.ArrayList;
import java.util.List;

public enum Class_0655 {
   WINTER(1, 2, 12),
   SPRING(3, 4, 5),
   SUMMER(6, 7, 8),
   AUTUMN(9, 10, 11);

   public final List<Integer> field_2111 = new ArrayList<>();

    Class_0655(int... var3) {
      for (int var7 : var3) {
         this.field_2111.add(var7);
      }
   }

   public boolean method_29(int var1) {
      return this.field_2111.contains(var1);
   }

   public static Class_0655 method_4(int var0) {
      for (Class_0655 var4 : values()) {
         if (var4.method_29(var0)) {
            return var4;
         }
      }

      throw new IllegalArgumentException("Unknown month");
   }
}
