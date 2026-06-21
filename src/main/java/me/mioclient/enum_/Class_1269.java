package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_1269 implements Class_0013 {
   PLAIN("Plain"),
   ALTERNATIVE("Alternative");

   public final String field_4033;

    Class_1269(String var3) {
      this.field_4033 = var3;
   }

   @Override
   public String getName() {
      return this.field_4033;
   }
}
