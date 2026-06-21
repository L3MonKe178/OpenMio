package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0829 implements Class_0013 {
   VANILLA("Vanilla"),
   TEXT("Text"),
   NONE("None");

   public final String field_2653;

    Class_0829(String var3) {
      this.field_2653 = var3;
   }

   @Override
   public String getName() {
      return this.field_2653;
   }
}
