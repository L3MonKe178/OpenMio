package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0525 implements Class_0013 {
   SOLID("Solid"),
   DOLPHIN("Dolphin");

   public final String field_1661;

    Class_0525(String var3) {
      this.field_1661 = var3;
   }

   @Override
   public String getName() {
      return this.field_1661;
   }
}
