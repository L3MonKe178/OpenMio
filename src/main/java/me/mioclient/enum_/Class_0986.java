package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0986 implements Class_0013 {
   NONE("None"),
   DOTS("Dots"),
   GRID("Grid");

   public final String field_3035;

    Class_0986(String var3) {
      this.field_3035 = var3;
   }

   @Override
   public String getName() {
      return this.field_3035;
   }
}
