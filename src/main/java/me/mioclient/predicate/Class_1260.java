package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.FakeLagModule;

public class Class_1260 implements Predicate {
   public FakeLagModule field_3957;

   public Class_1260(FakeLagModule var1) {
      super();
      this.field_3957 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3957.field_3050.method_194();
   }
}
