package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0766 implements Class_0013 {
   STEAL("Steal"),
   FILL("Fill"),
   DROP("Drop"),
   REFILL("Refill"),
   REKIT("Rekit");

   public final String field_2411;

    Class_0766(String var3) {
      this.field_2411 = var3;
   }

   @Override
   public String getName() {
      return this.field_2411;
   }
}
