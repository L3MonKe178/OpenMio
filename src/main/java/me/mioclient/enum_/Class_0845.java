package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0845 implements Nameable {
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
