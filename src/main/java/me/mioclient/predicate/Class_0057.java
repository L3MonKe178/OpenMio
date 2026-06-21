package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0062;
import me.mioclient.module.render.ESPModule;

public class Class_0057 implements Predicate {
   public ESPModule field_142;

   public Class_0057(ESPModule var1) {
      super();
      this.field_142 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return (this.field_142.field_3825.getValue() == Class_0062.TEXT || this.field_142.field_3825.getValue() == Class_0062.BOTH)
         && this.field_142.field_3809.method_194()
         && this.field_142.field_3824.method_194();
   }
}
