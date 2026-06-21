package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0753 implements Class_0013 {
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
