package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.exploit.FastProjectileModule;

public class Class_0607 implements Predicate {
   public FastProjectileModule field_1881;

   public Class_0607(FastProjectileModule var1) {
      super();
      this.field_1881 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1881.field_1482.method_194();
   }
}
