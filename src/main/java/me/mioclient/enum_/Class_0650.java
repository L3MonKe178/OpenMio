package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0650 implements Class_0013 {
   NONE("None", 0),
   X2("X2", 2),
   X4("X4", 4),
   X8("X8", 8);

   public final String field_2073;
   public final int field_2074;

    Class_0650(String var3, int var4) {
      this.field_2073 = var3;
      this.field_2074 = var4;
   }

   @Override
   public String getName() {
      return this.field_2073;
   }

   public int method_658() {
      return this.field_2074;
   }
}
