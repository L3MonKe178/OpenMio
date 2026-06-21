package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0046 implements Class_0013 {
   TOGGLE("Toggle", "toggle"),
   HOLD("Hold", "hold"),
   HOLD_REVERSE("ReverseHold", "hold_reverse");

   public final String field_95;
   public final String field_96;

    Class_0046(String var3, String var4) {
      this.field_95 = var3;
      this.field_96 = var4;
   }

   public String method_30() {
      return this.field_96;
   }

   @Override
   public String getName() {
      return this.field_95;
   }

   public static Class_0046 method_4(String var0) {
      String var1 = var0.trim().toLowerCase();

      return switch (var1) {
         case "hold" -> HOLD;
         case "hold_reverse" -> HOLD_REVERSE;
         default -> TOGGLE;
      };
   }
}
