package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0446 implements Nameable {
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
