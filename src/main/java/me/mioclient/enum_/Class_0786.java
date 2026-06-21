package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0786 implements Nameable {
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
