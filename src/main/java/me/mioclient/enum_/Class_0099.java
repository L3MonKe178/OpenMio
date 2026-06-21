package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0099 implements Class_0013 {
   BLINK("Blink"),
   PULSE("Pulse");

   public final String field_312;

    Class_0099(String var3) {
      this.field_312 = var3;
   }

   @Override
   public String getName() {
      return this.field_312;
   }
}
