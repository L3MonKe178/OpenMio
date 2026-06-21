package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_1025 implements Class_0013 {
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
