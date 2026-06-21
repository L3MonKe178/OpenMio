package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0685 implements Class_0013 {
   NONE("None"),
   NORMAL("Normal"),
   SILENT("Silent");

   public final String field_2186;

    Class_0685(String var3) {
      this.field_2186 = var3;
   }

   @Override
   public String getName() {
      return this.field_2186;
   }
}
