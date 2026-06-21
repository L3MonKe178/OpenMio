package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0927 implements Class_0013 {
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
