package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0760 implements Nameable {
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
