package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.exploit.FastProjectileModule;

public class Class_1106 implements Predicate {
   public FastProjectileModule field_3414;

   public Class_1106(FastProjectileModule var1) {
      super();
      this.field_3414 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3414.field_1482.method_194();
   }
}
