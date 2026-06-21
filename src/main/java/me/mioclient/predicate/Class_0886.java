package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.exploit.FastProjectileModule;

public class Class_0886 implements Predicate {
   public FastProjectileModule field_2807;

   public Class_0886(FastProjectileModule var1) {
      super();
      this.field_2807 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2807.field_1482.method_194();
   }
}
