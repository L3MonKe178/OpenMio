package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0285 implements Nameable {
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
