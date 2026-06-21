package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0581;
import me.mioclient.module.movement.StepModule;

public class Class_0157 implements Predicate {
   public StepModule field_458;

   public Class_0157(StepModule var1) {
      super();
      this.field_458 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_458.field_2120.getValue() == Class_0581.NORMAL;
   }
}
