package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0614 implements Class_0013 {
   NONE("None"),
   NCP("NCP"),
   GRIM("Grim"),
   GRIMV3("Slow"),
   CONSTANTIAM("Constantiam");

   public final String field_1903;

    Class_0614(String var3) {
      this.field_1903 = var3;
   }

   @Override
   public String getName() {
      return this.field_1903;
   }
}
