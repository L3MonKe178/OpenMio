package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0474;
import me.mioclient.module.movement.SprintModule;

public class Class_0707 implements Predicate {
   public SprintModule field_2238;

   public Class_0707(SprintModule var1) {
      super();
      this.field_2238 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2238.field_1022.getValue() != Class_0474.INSTANT;
   }
}
