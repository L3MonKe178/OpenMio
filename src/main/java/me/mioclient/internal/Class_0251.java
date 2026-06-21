package me.mioclient.internal;

import java.util.ArrayList;
import java.util.List;

public class Class_0251 {
   public Class_0251() {
      super();
   }

   public static List<String> method_9(String var0, double var1) {
      ArrayList var3 = new ArrayList();
      StringBuilder var4 = new StringBuilder();
      char var5 = '\uffff';

      for (int var6 = 0; var6 < var0.length(); var6++) {
         char var7 = var0.charAt(var6);
         if (var7 == 167 && var6 < var0.length() - 1) {
            var5 = var0.charAt(var6 + 1);
         }

         if ((double)FontRenderer.field_3143.method_221(new TextBuilder().method_2(var7).method_2(var4.toString()).method_9("\u0001\u0001")) < var1) {
            var4.append(var7);
         } else {
            var3.add(var4.toString());
            var4 = new StringBuilder().append('§').append(var5).append(var7);
         }
      }

      if (var4.length() > 0) {
         var3.add(var4.toString());
      }

      return var3;
   }

   public static String[] method_100(String var0) {
      ArrayList<String> var1 = new ArrayList<>(1);
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < var0.length(); var3++) {
         char var4 = var0.charAt(var3);
         if (var4 == '\n') {
            var1.add(var2.toString());
            var2 = new StringBuilder();
         } else {
            var2.append(var4);
         }
      }

      var1.add(var2.toString());
      return var1.toArray(new String[0]);
   }

   public static Character method_101(String var0) {
      char var1 = ' ';

      for (int var2 = 0; var2 < var0.length(); var2++) {
         char var3 = var0.charAt(var2);
         if (var3 == 167 && var2 + 1 < var0.length()) {
            var1 = var0.charAt(var2 + 1);
         }
      }

      return var1;
   }
}
