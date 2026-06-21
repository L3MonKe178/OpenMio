package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_1360 implements Class_0013 {
   TELEPORT("Teleport"),
   CANCEL("Cancel");

   public final String field_4431;

    Class_1360(String var3) {
      this.field_4431 = var3;
   }

   @Override
   public String getName() {
      return this.field_4431;
   }
}
