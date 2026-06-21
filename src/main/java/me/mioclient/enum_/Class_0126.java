package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0126 implements Nameable {
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
