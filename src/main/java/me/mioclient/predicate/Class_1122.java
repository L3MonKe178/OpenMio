package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0474;
import me.mioclient.module.movement.SprintModule;

public class Class_1122 implements Predicate {
   public SprintModule field_3476;

   public Class_1122(SprintModule var1) {
      super();
      this.field_3476 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3476.field_1022.getValue() == Class_0474.INSTANT && this.field_3476.field_1025.method_194();
   }
}
