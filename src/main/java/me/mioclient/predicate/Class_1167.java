package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.NoSlowModule;

public class Class_1167 implements Predicate {
   public NoSlowModule field_3620;

   public Class_1167(NoSlowModule var1) {
      super();
      this.field_3620 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3620.field_1694.method_194();
   }
}
