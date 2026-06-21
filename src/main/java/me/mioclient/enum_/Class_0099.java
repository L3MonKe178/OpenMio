package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0099 implements Nameable {
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
