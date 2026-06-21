package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_1379 implements Nameable {
   CUSTOM("Custom"),
   RANDOM("Random");

   public final String field_4486;

    Class_1379(String var3) {
      this.field_4486 = var3;
   }

   @Override
   public String getName() {
      return this.field_4486;
   }
}
