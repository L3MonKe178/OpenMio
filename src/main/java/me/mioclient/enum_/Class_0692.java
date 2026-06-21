package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0692 implements Class_0013 {
   NONE("None"),
   FAST("Fast"),
   INSTANT("Instant");

   public final String field_2203;

    Class_0692(String var3) {
      this.field_2203 = var3;
   }

   @Override
   public String getName() {
      return this.field_2203;
   }
}
