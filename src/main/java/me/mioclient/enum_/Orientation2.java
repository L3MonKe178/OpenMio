package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Orientation2 implements Class_0013 {
   FULL("Full"),
   VERTICAL("Vertical"),
   HORIZONTAL("Horizontal"),
   RANDOM("Random");

   public final String field_2446;

    Orientation2(String var3) {
      this.field_2446 = var3;
   }

   @Override
   public String getName() {
      return this.field_2446;
   }
}
