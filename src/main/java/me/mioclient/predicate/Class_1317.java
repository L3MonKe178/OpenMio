package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0062;
import me.mioclient.module.render.ESPModule;

public class Class_1317 implements Predicate {
   public ESPModule field_4242;

   public Class_1317(ESPModule var1) {
      super();
      this.field_4242 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4242.field_3825.getValue() == Class_0062.TEXT && this.field_4242.field_3809.method_194() && this.field_4242.field_3824.method_194();
   }
}
