package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0323 implements Class_0013 {
   NONE("None"),
   PLAIN("Plain"),
   STRICT("Strict");

   public final String field_1091;

    Class_0323(String var3) {
      this.field_1091 = var3;
   }

   @Override
   public String getName() {
      return this.field_1091;
   }
}
