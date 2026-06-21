package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0403 implements Nameable {
   NONE("None"),
   NORMAL("Normal"),
   SILENT("Silent");

   public final String field_1299;

    Class_0403(String var3) {
      this.field_1299 = var3;
   }

   @Override
   public String getName() {
      return this.field_1299;
   }
}
