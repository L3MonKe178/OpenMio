package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0768 implements Class_0013 {
   NONE("None"),
   REQUIRE("Require"),
   SWAP("Swap");

   public final String field_2417;

    Class_0768(String var3) {
      this.field_2417 = var3;
   }

   @Override
   public String getName() {
      return this.field_2417;
   }
}
