package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Orientation implements Class_0013 {
   VERTICAL("Vertical"),
   HORIZONTAL("Horizontal"),
   BOTH("Both"),
   BOUNCE("Bounce"),
   NONE("None");

   public final String field_544;

    Orientation(String var3) {
      this.field_544 = var3;
   }

   @Override
   public String getName() {
      return this.field_544;
   }
}
