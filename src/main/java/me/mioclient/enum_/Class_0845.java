package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0845 implements Class_0013 {
   NONE("None"),
   PLAIN("Plain"),
   MANUAL("Manual"),
   STRICT("Strict");

   public final String field_2710;

    Class_0845(String var3) {
      this.field_2710 = var3;
   }

   @Override
   public String getName() {
      return this.field_2710;
   }
}
