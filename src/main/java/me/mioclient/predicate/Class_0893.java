package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0474;
import me.mioclient.module.movement.SprintModule;

public class Class_0893 implements Predicate {
   public SprintModule field_2819;

   public Class_0893(SprintModule var1) {
      super();
      this.field_2819 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2819.field_1022.getValue() == Class_0474.INSTANT;
   }
}
