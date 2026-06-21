package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0861 implements Class_0013 {
   DISTANCE("Distance"),
   ANGLE("Angle");

   public final String field_2772;

    Class_0861(String var3) {
      this.field_2772 = var3;
   }

   @Override
   public String getName() {
      return this.field_2772;
   }
}
