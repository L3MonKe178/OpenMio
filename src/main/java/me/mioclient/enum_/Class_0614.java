package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0614 implements Nameable {
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
