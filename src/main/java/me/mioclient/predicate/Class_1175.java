package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0062;
import me.mioclient.module.render.ESPModule;

public class Class_1175 implements Predicate {
   public ESPModule field_3639;

   public Class_1175(ESPModule var1) {
      super();
      this.field_3639 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3639.field_3825.getValue() == Class_0062.TEXT || this.field_3639.field_3825.getValue() == Class_0062.BOTH;
   }
}
