package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0753 implements Nameable {
   PLAIN("Plain"),
   STRICT("Strict"),
   INSTANT("Instant");

   public final String field_2390;

    Class_0753(String var3) {
      this.field_2390 = var3;
   }

   @Override
   public String getName() {
      return this.field_2390;
   }
}
