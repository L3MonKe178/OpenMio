package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0062;
import me.mioclient.module.render.ESPModule;

public class Class_0164 implements Predicate {
   public ESPModule field_481;

   public Class_0164(ESPModule var1) {
      super();
      this.field_481 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return (this.field_481.field_3825.getValue() == Class_0062.BOX || this.field_481.field_3825.getValue() == Class_0062.BOTH)
         && this.field_481.field_3809.method_194()
         && this.field_481.field_3824.method_194();
   }
}
