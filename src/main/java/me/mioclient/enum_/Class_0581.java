package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0581 implements Class_0013 {
   VANILLA("Vanilla"),
   NORMAL("Normal");

   public final String field_1841;

    Class_0581(String var3) {
      this.field_1841 = var3;
   }

   @Override
   public String getName() {
      return this.field_1841;
   }
}
