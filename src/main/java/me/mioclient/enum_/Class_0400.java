package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0400 implements Class_0013 {
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
