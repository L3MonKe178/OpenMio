package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0446 implements Class_0013 {
   ALWAYS("Always"),
   STRICT("Strict"),
   NONE("None");

   public final String field_1433;

    Class_0446(String var3) {
      this.field_1433 = var3;
   }

   @Override
   public String getName() {
      return this.field_1433;
   }
}
