package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0760 implements Class_0013 {
   KEEP("Keep"),
   MOVE("Move"),
   HIDE("Hide");

   public final String field_2399;

    Class_0760(String var3) {
      this.field_2399 = var3;
   }

   @Override
   public String getName() {
      return this.field_2399;
   }
}
