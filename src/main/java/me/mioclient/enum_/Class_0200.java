package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0200 implements Class_0013 {
   OVERWORLD("overworld", "Overworld"),
   THE_NETHER("the_nether", "Nether"),
   THE_END("the_end", "End");

   public final String field_559;
   public final String field_560;

    Class_0200(String var3, String var4) {
      this.field_559 = var3;
      this.field_560 = var4;
   }

   public boolean method_232() {
      return this == OVERWORLD;
   }

   public boolean method_233() {
      return this == THE_NETHER;
   }

   public boolean method_234() {
      return this == THE_END;
   }

   public String method_235() {
      return this.field_560;
   }

   @Override
   public String getName() {
      return this.field_559;
   }
}
