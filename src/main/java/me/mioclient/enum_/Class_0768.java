package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0768 implements Nameable {
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
