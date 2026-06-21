package me.mioclient.enum_;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import me.mioclient.api.Class_0013;

public enum Class_0341 implements Class_0013 {
   HH(new SimpleDateFormat("HH"), "Hours"),
   HH_MM(new SimpleDateFormat("HH:mm"), "Minutes"),
   HH_MM_SS(new SimpleDateFormat("HH:mm:ss"), "Seconds");

   public final DateFormat field_1139;
   public final String field_1140;

    Class_0341(DateFormat var3, String var4) {
      this.field_1139 = var3;
      this.field_1140 = var4;
   }

   public DateFormat method_389() {
      return this.field_1139;
   }

   public String method_2(Date var1) {
      return this.field_1139.format(var1);
   }

   @Override
   public String getName() {
      return this.field_1140;
   }
}
