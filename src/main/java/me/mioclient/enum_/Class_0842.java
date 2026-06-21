package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0842 implements Nameable {
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
