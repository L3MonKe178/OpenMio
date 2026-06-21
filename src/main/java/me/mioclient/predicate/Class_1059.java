package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0474;
import me.mioclient.module.movement.SprintModule;

public class Class_1059 implements Predicate {
   public SprintModule field_3260;

   public Class_1059(SprintModule var1) {
      super();
      this.field_3260 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3260.field_1022.getValue() == Class_0474.INSTANT;
   }
}
