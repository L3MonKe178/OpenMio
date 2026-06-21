package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.NoSlowModule;

public class Class_1184 implements Predicate {
   public NoSlowModule field_3667;

   public Class_1184(NoSlowModule var1) {
      super();
      this.field_3667 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3667.field_1694.method_194();
   }
}
