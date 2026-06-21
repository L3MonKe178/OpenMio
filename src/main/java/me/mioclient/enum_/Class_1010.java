package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_1010 implements Class_0013 {
   NCP("NCP"),
   GRIM("Grim");

   public final String field_3114;

    Class_1010(String var3) {
      this.field_3114 = var3;
   }

   @Override
   public String getName() {
      return this.field_3114;
   }
}
