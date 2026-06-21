package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0474;
import me.mioclient.module.movement.SprintModule;

public class Class_0946 implements Predicate {
   public SprintModule field_2950;

   public Class_0946(SprintModule var1) {
      super();
      this.field_2950 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2950.field_1022.getValue() == Class_0474.INSTANT;
   }
}
