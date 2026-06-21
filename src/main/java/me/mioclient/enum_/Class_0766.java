package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0766 implements Nameable {
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
