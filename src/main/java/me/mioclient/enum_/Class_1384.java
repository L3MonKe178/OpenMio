package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_1384 implements Class_0013 {
   NONE("None"),
   STRICT("Strict"),
   STRONG("Strong");

   public final String field_4492;

    Class_1384(String var3) {
      this.field_4492 = var3;
   }

   @Override
   public String getName() {
      return this.field_4492;
   }

   public boolean method_1214() {
      return this != NONE;
   }
}
