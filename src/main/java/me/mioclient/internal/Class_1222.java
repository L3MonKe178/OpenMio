package me.mioclient.internal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Class_1222 {
   public Class_1222() {
      super();
   }

   public static Path method_2(Path var0, String... var1) {
      if (var0.toFile().exists()) {
         return var0;
      } else {
         for (String var5 : var1) {
            String var6 = new Class_1303().method_2((Object)var5).method_2(var0.toString()).method_9("\u0001\u0001");
            Path var7 = Path.of(var6);
            if (var7.toFile().exists()) {
               return var7;
            }
         }

         return var0;
      }
   }

   public static void method_2(Path var0, String var1) throws java.io.IOException {
      method_2(var0, var1.getBytes(StandardCharsets.UTF_8));
   }

   public static void method_2(Path var0, byte[] var1) throws java.io.IOException {
      FileOutputStream var2 = null;

      try {
         var2 = new FileOutputStream(var0.toFile());
         var2.write(var1);
         var2.close();
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (Exception var9) {
            }
         }
      }
   }

   public static byte[] method_2(Path var0) throws java.io.IOException {
      FileInputStream var1 = null;
      byte[] var2 = null;

      try {
         var1 = new FileInputStream(var0.toFile());
         var2 = var1.readAllBytes();
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (Exception var9) {
            }
         }
      }

      return (byte[])var2;
   }

   public static String method_9(Path var0) throws java.io.IOException {
      return new String(method_2(var0), StandardCharsets.UTF_8);
   }
}
