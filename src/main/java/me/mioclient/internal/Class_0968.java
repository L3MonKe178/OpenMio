package me.mioclient.internal;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Class_0968 {
   public static SecretKey field_2976;
   public static IvParameterSpec field_2977;

   public Class_0968() {
      super();
   }

   public static void reset() {
      field_2976 = null;
      field_2977 = null;
   }

   public static SecretKey method_175() {
      return field_2976;
   }

   public static IvParameterSpec method_253() {
      return field_2977;
   }

   public static void method_2(byte[] var0, byte[] var1) {
      field_2976 = new SecretKeySpec(var0, "AES");
      field_2977 = new IvParameterSpec(var1);
   }

   public static boolean method_869() {
      return field_2976 != null;
   }
}
