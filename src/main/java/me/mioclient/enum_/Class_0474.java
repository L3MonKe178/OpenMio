package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0474 implements Nameable {
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
