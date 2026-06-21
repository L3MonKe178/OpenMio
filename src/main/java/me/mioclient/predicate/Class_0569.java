package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.NoSlowModule;

public class Class_0569 implements Predicate {
   public NoSlowModule field_1798;

   public Class_0569(NoSlowModule var1) {
      super();
      this.field_1798 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1798.field_1694.method_194();
   }
}
