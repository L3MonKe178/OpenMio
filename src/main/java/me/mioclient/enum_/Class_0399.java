package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0399 implements Class_0013 {
   HOLD("Hold"),
   INSTANT("Instant"),
   NONE("None");

   public final String field_1287;

    Class_0399(String var3) {
      this.field_1287 = var3;
   }

   @Override
   public String getName() {
      return this.field_1287;
   }
}
