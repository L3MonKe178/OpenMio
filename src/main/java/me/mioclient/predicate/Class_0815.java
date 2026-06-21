package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.exploit.FastProjectileModule;

public class Class_0815 implements Predicate {
   public FastProjectileModule field_2584;

   public Class_0815(FastProjectileModule var1) {
      super();
      this.field_2584 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2584.field_1482.method_194();
   }
}
