package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0555 implements Class_0013 {
   AVOID("Avoid"),
   STARE("Stare");

   public final String field_1765;

    Class_0555(String var3) {
      this.field_1765 = var3;
   }

   @Override
   public String getName() {
      return this.field_1765;
   }
}
