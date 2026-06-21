package me.mioclient.internal;

import javax.crypto.Cipher;

public class Class_0338 {
   public Class_0338() {
      super();
   }

   public static byte[] method_37(byte[] var0) {
      try {
         Cipher var1 = Cipher.getInstance("AES/CBC/PKCS5Padding");
         var1.init(1, Class_0968.method_175(), Class_0968.method_253());
         byte[] var2 = var1.doFinal(var0);
         method_34(var2);
         return var2;
      } catch (Exception e) { throw new RuntimeException(e); }
   }

   public static byte[] method_33(byte[] var0) {
      try {
         Cipher var1 = Cipher.getInstance("AES/CBC/PKCS5Padding");
         var1.init(2, Class_0968.method_175(), Class_0968.method_253());
         method_34(var0);
         return var1.doFinal(var0);
      } catch (Exception e) { throw new RuntimeException(e); }
   }

   public static void method_34(byte[] var0) {
      if (var0 != null) {
         int var1 = 0;

         for (int var2 = var0.length - 1; var2 > var1; var1++) {
            byte var3 = var0[var2];
            var0[var2] = var0[var1];
            var0[var1] = var3;
            var2--;
         }
      }
   }
}
