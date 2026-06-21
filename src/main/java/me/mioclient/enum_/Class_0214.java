package me.mioclient.enum_;

import me.mioclient.api.Class_0013;
import me.mioclient.api.Class_1153;
import me.mioclient.module.abstract_.AbstractModule_42;

public enum Class_0214 implements Class_0013 {
   PLAIN("Plain"),
   SMART("Smart");

   public final String field_600;

    Class_0214(String var3) {
      this.field_600 = var3;
   }

   @Override
   public String getName() {
      return this.field_600;
   }

   public Class_1153 method_2(AbstractModule_42 var1) {
      return this == PLAIN ? var1.field_779 : var1.field_780;
   }
}
