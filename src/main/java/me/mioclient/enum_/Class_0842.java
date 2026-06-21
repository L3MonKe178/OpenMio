package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0842 implements Class_0013 {
   NONE("None"),
   SIMPLE("Simple"),
   COMPLEX("Complex"),
   BOTH("Both");

   public final String field_2702;

    Class_0842(String var3) {
      this.field_2702 = var3;
   }

   @Override
   public String getName() {
      return this.field_2702;
   }
}
