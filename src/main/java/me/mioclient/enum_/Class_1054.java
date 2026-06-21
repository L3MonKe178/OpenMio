package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_1054 implements Class_0013 {
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
