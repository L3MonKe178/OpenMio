package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Orientation implements Nameable {
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
