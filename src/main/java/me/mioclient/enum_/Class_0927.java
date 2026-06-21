package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0927 implements Nameable {
   NONE("None"),
   COORDINATES("Coordinates"),
   DISTANCE("Distance");

   public final String field_2907;

    Class_0927(String var3) {
      this.field_2907 = var3;
   }

   @Override
   public String getName() {
      return this.field_2907;
   }
}
