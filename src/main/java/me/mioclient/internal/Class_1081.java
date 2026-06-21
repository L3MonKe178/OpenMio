package me.mioclient.internal;

import java.awt.Color;
import me.mioclient.module.client.UIModule;

public class Class_1081 {
   public static final Color field_3321 = new Color(0, 0, 0, 0);

   public Class_1081() {
      super();
   }

   public static Color method_959() {
      return UIModule.field_2843.field_2879.getValue();
   }

   public static Color method_2(int var0, float var1, float var2, int var3) {
      return method_2(Class_0245.field_686, var0, var1, var2, var3);
   }

   public static Color method_2(int var0, int var1, float var2, float var3, int var4) {
      double var5 = Math.ceil((double)(System.currentTimeMillis() + (long)var1) / 20.0);
      var5 %= (double)var0;
      Color var7 = Color.getHSBColor((float)(var5 / (double)var0), var2, var3);
      return method_9(var7, var4);
   }

   public static Color method_2(Color var0, Color var1, double var2, double var4) {
      double var6 = ((double)System.currentTimeMillis() + var4) % var2 / var2;
      return var6 > Class_0245.field_688 ? method_2(var1, var0, (float)((var6 - Class_0245.field_688) * 2.0)) : method_2(var0, var1, (float)(var6 * 2.0));
   }

   public static Color method_2(Color var0, Color var1, float var2) {
      try {
         return new Color(
            (int)((float)var0.getRed() * var2 + (float)var1.getRed() * (1.0F - var2)),
            (int)((float)var0.getGreen() * var2 + (float)var1.getGreen() * (1.0F - var2)),
            (int)((float)var0.getBlue() * var2 + (float)var1.getBlue() * (1.0F - var2)),
            (int)((float)var0.getAlpha() * var2 + (float)var1.getAlpha() * (1.0F - var2))
         );
      } catch (IllegalArgumentException var4) {
         var4.printStackTrace();
         return Color.white;
      }
   }

   public static Color method_9(Color var0, int var1) {
      return new Color(var0.getRed(), var0.getGreen(), var0.getBlue(), var1);
   }

   public static Color method_2(Color var0, float var1) {
      return method_9(var0, Math.round(var1 * 255.0F));
   }

   public static int method_5(Color var0, int var1) {
      return method_2(var0.getRed(), var0.getGreen(), var0.getBlue(), var1);
   }

   public static int method_9(Color var0, float var1) {
      return method_2(var0.getRed(), var0.getGreen(), var0.getBlue(), (int)(var1 * 255.0F));
   }

   public static Color method_29(Color var0) {
      return method_9(var0, 0);
   }

   public static int method_2(int var0, int var1, int var2, int var3) {
      return (var3 & 0xFF) << 24 | (var0 & 0xFF) << 16 | (var1 & 0xFF) << 8 | var2 & 0xFF;
   }

   public static int method_2(float var0, float var1, float var2, float var3) {
      return ((int)(var3 * 255.0F) & 0xFF) << 24 | ((int)(var0 * 255.0F) & 0xFF) << 16 | ((int)(var1 * 255.0F) & 0xFF) << 8 | (int)(var2 * 255.0F) & 0xFF;
   }

   public static Color method_37(int var0) {
      float var1 = (float)(var0 >> 24 & 0xFF);
      float var2 = (float)(var0 >> 16 & 0xFF);
      float var3 = (float)(var0 >> 8 & 0xFF);
      float var4 = (float)(var0 & 0xFF);
      return new Color(var2, var3, var4, var1);
   }

   public static Color method_5(Color var0, float var1) {
      return new Color(
         Math.max((int)((float)var0.getRed() * var1), 0),
         Math.max((int)((float)var0.getGreen() * var1), 0),
         Math.max((int)((float)var0.getBlue() * var1), 0),
         var0.getAlpha()
      );
   }

   public static Color method_7(Color var0, float var1) {
      int var2 = var0.getRed();
      int var3 = var0.getGreen();
      int var4 = var0.getBlue();
      int var5 = var0.getAlpha();
      int var6 = (int)(1.0 / (1.0 - (double)var1));
      if (var2 == 0 && var3 == 0 && var4 == 0) {
         return new Color(var6, var6, var6, var5);
      } else {
         if (var2 > 0 && var2 < var6) {
            var2 = var6;
         }

         if (var3 > 0 && var3 < var6) {
            var3 = var6;
         }

         if (var4 > 0 && var4 < var6) {
            var4 = var6;
         }

         return new Color(Math.min((int)((float)var2 / var1), 255), Math.min((int)((float)var3 / var1), 255), Math.min((int)((float)var4 / var1), 255), var5);
      }
   }

   public static String method_2(Color var0, boolean var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append(method_33(var0.getAlpha()));
      var2.append(method_33(var0.getRed()));
      var2.append(method_33(var0.getGreen()));
      var2.append(method_33(var0.getBlue()));
      return (var1 ? "#" : "") + var2;
   }

   public static String method_33(int var0) {
      return (var0 < 16 ? "0" : "") + Integer.toHexString(var0);
   }

   public static Color method_209(String var0) {
      if (var0.length() < 6) {
         throw new IllegalArgumentException();
      } else {
         if (var0.startsWith("#")) {
            var0 = var0.substring(1);
         }

         boolean var1 = var0.length() == 8;
         int[] var2 = new int[var1 ? 4 : 3];

         for (int var3 = 0; var3 < (var1 ? 4 : 3); var3++) {
            var2[var3] = Integer.parseInt(var0.substring(var3 * 2, (var3 + 1) * 2), 16);
         }

         int var4 = var1 ? 1 : 0;
         return new Color(var2[var4], var2[1 + var4], var2[2 + var4], var1 ? var2[0] : 255);
      }
   }
}
