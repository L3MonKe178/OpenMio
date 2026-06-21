package me.mioclient.clickgui.util;

import java.awt.Color;

public final class ColorUtil {
   private ColorUtil() {}

   public static int argb(Color c) { return c == null ? 0 : c.getRGB(); }

   public static int withAlpha(Color c, int alpha) {
      if (c == null) return 0;
      return (alpha & 0xFF) << 24 | (c.getRed() & 0xFF) << 16 | (c.getGreen() & 0xFF) << 8 | (c.getBlue() & 0xFF);
   }

   public static int withAlpha(int argb, int alpha) {
      return (alpha & 0xFF) << 24 | (argb & 0x00FFFFFF);
   }

   public static int withAlphaMul(Color c, float mul) {
      if (c == null) return 0;
      int a = Math.round(c.getAlpha() * Math.max(0F, Math.min(1F, mul)));
      return withAlpha(c, a);
   }

   public static int lerp(int a, int b, float t) {
      t = Math.max(0F, Math.min(1F, t));
      int aa = (a >>> 24) & 0xFF, ar = (a >>> 16) & 0xFF, ag = (a >>> 8) & 0xFF, ab = a & 0xFF;
      int ba = (b >>> 24) & 0xFF, br = (b >>> 16) & 0xFF, bg = (b >>> 8) & 0xFF, bb = b & 0xFF;
      int oa = Math.round(aa + (ba - aa) * t);
      int or = Math.round(ar + (br - ar) * t);
      int og = Math.round(ag + (bg - ag) * t);
      int ob = Math.round(ab + (bb - ab) * t);
      return (oa & 0xFF) << 24 | (or & 0xFF) << 16 | (og & 0xFF) << 8 | (ob & 0xFF);
   }
}
