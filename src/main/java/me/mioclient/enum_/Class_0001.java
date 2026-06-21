package me.mioclient.enum_;

import me.mioclient.api.Class_0013;

public enum Class_0001 implements Class_0013 {
   NORMAL("Normal"),
   SWAPBACK("SwapBack"),
   SILENT("Silent");

   public final String field_4;

    Class_0001(String var3) {
      this.field_4 = var3;
   }

   @Override
   public String getName() {
      return this.field_4;
   }
}
