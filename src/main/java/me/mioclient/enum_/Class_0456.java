package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_0456 implements Nameable {
   NORMAL("Normal"),
   SILENT("Silent");

   public final String field_1459;

    Class_0456(String var3) {
      this.field_1459 = var3;
   }

   @Override
   public String getName() {
      return this.field_1459;
   }
}
