package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0829 implements Nameable {
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
