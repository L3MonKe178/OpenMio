package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_1140 implements Nameable {
   NORMAL("Normal"),
   ALTERNATIVE("Alternative"),
   PICK("Pick");

   public final String field_3527;

    Class_1140(String var3) {
      this.field_3527 = var3;
   }

   @Override
   public String getName() {
      return this.field_3527;
   }
}
