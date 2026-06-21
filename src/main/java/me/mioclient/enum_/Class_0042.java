package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0042 implements Class_0013 {
   PLAIN("Plain"),
   STRICT("Strict"),
   LAGBACK("LagBack"),
   GRIM("Grim");

   public final String field_86;

    Class_0042(String var3) {
      this.field_86 = var3;
   }

   @Override
   public String getName() {
      return this.field_86;
   }
}
