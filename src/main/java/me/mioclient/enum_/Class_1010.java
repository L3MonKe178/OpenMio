package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_1010 implements Nameable {
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
