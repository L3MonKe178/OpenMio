package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.exploit.FastProjectileModule;

public class Class_0237 implements Predicate {
   public FastProjectileModule field_650;

   public Class_0237(FastProjectileModule var1) {
      super();
      this.field_650 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_650.field_1482.method_194();
   }
}
