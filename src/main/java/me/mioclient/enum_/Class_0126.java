package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0126 implements Class_0013 {
   PARTIAL("Partial"),
   FULL("Full");

   public final String field_377;

    Class_0126(String var3) {
      this.field_377 = var3;
   }

   @Override
   public String getName() {
      return this.field_377;
   }
}
