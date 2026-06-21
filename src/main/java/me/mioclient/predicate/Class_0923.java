package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0581;
import me.mioclient.module.movement.StepModule;

public class Class_0923 implements Predicate {
   public StepModule field_2902;

   public Class_0923(StepModule var1) {
      super();
      this.field_2902 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2902.field_2120.getValue() == Class_0581.NORMAL && this.field_2902.field_2123.method_194();
   }
}
