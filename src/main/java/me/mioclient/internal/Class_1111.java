package me.mioclient.internal;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class Class_1111 {
   public static final byte[] field_3431 = new byte[]{
      7,
      77,
      -33,
      27,
      63,
      -73,
      114,
      -122,
      16,
      41,
      4,
      -128,
      -51,
      -25,
      42,
      -49,
      74,
      -112,
      -93,
      12,
      96,
      119,
      -106,
      -11,
      -26,
      15,
      -109,
      -37,
      122,
      -104,
      -16,
      62,
      59,
      101,
      -102,
      -81,
      3,
      47,
      -68,
      -77,
      -1,
      24,
      90,
      100,
      -23,
      -109,
      -107,
      -86,
      -126,
      64,
      -115,
      -66,
      -128,
      -96,
      75,
      28,
      -95,
      50,
      -118,
      -24,
      80,
      -126,
      -29,
      2,
      77,
      26,
      122,
      -32,
      -2,
      94,
      -100,
      -27,
      -46,
      102,
      -103,
      -36,
      102,
      -55,
      -32,
      -23,
      79,
      -70,
      36,
      -50,
      40,
      -60,
      -50,
      -56,
      -10,
      48,
      83,
      89,
      53,
      91,
      -26,
      -73
   };

   public Class_1111() {
      super();
      throw new AssertionError();
   }

   public static byte[] method_35(byte[] var0) {
      byte[] var1 = new byte[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = (byte)(var0[var2] ^ field_3431[var2 % field_3431.length]);
      }

      return var1;
   }

   public static String method_340(String var0) {
      try {
         return new String(Base64.getEncoder().encode(method_35(var0.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }

   public static String method_99(String var0) {
      try {
         return new String(method_35(Base64.getDecoder().decode(var0)), StandardCharsets.UTF_8);
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }
}
