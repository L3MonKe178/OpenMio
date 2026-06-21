package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0062 implements Class_0013 {
   BOX("Box"),
   TEXT("Text"),
   BOTH("Both");

   public final String field_187;

    Class_0062(String var3) {
      this.field_187 = var3;
   }

   @Override
   public String getName() {
      return this.field_187;
   }
}
