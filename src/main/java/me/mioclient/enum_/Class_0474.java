package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0474 implements Class_0013 {
   LEGIT("Legit"),
   RAGE("Rage"),
   INSTANT("Instant");

   public final String field_1516;

    Class_0474(String var3) {
      this.field_1516 = var3;
   }

   @Override
   public String getName() {
      return this.field_1516;
   }
}
