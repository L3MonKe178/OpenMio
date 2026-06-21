package me.mioclient.enum_;

import me.mioclient.api.Nameable;

public enum Class_1054 implements Nameable {
   MOTION("Motion"),
   SILENT("Silent");

   public final String field_3252;

    Class_1054(String var3) {
      this.field_3252 = var3;
   }

   @Override
   public String getName() {
      return this.field_3252;
   }
}
