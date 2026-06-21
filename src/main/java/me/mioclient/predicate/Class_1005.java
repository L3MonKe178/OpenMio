package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.NoSlowModule;

public class Class_1005 implements Predicate {
   public NoSlowModule field_3095;

   public Class_1005(NoSlowModule var1) {
      super();
      this.field_3095 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3095.field_1694.method_194() && this.field_3095.field_1696.getValue();
   }
}
