package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_1168 implements Class_0013 {
   ANY("Any"),
   TOOLS("Tools"),
   ARMOR("Armor");

   public final String field_3624;

    Class_1168(String var3) {
      this.field_3624 = var3;
   }

   @Override
   public String getName() {
      return this.field_3624;
   }
}
