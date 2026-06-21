package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0285 implements Class_0013 {
   NONE("None"),
   NORMAL("Normal"),
   SILENT("Silent");

   public final String field_916;

    Class_0285(String var3) {
      this.field_916 = var3;
   }

   @Override
   public String getName() {
      return this.field_916;
   }
}
