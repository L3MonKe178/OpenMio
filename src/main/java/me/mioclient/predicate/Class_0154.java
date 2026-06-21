package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0062;
import me.mioclient.module.render.ESPModule;

public class Class_0154 implements Predicate {
   public ESPModule field_445;

   public Class_0154(ESPModule var1) {
      super();
      this.field_445 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_445.field_3825.getValue() == Class_0062.TEXT && this.field_445.field_3809.method_194() && this.field_445.field_3824.method_194();
   }
}
