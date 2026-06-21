package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0286 implements Nameable {
   VANILLA("Vanilla"),
   ONE_EIGHT("1.8"),
   ONE_TWELVE("1.12");

   public final String field_921;

    Class_0286(String var3) {
      this.field_921 = var3;
   }

   @Override
   public String getName() {
      return this.field_921;
   }
}
