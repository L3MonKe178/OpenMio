package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_1025 implements Nameable {
   SHOW("Show"),
   ONLY("Only"),
   HIDE("Hide");

   public final String field_3179;

    Class_1025(String var3) {
      this.field_3179 = var3;
   }

   @Override
   public String getName() {
      return this.field_3179;
   }
}
