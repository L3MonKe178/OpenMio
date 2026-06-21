package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0647 implements Class_0013 {
   NONE("None"),
   FLAT("Flat"),
   END("End");

   public final String field_2067;

    Class_0647(String var3) {
      this.field_2067 = var3;
   }

   @Override
   public String getName() {
      return this.field_2067;
   }
}
