package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0786 implements Class_0013 {
   NONE("None"),
   SOLID("Solid"),
   GRADIENT("Gradient");

   public final String field_2460;

    Class_0786(String var3) {
      this.field_2460 = var3;
   }

   @Override
   public String getName() {
      return this.field_2460;
   }
}
