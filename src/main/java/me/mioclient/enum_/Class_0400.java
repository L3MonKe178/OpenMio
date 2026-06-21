package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0400 implements Nameable {
   OFF("Off"),
   TICK("Tick"),
   SLOW("Slow");

   public final String field_1292;

    Class_0400(String var3) {
      this.field_1292 = var3;
   }

   @Override
   public String getName() {
      return this.field_1292;
   }
}
