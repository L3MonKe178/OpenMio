package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.FakeLagModule;

public class Class_0995 implements Predicate {
   public FakeLagModule field_3083;

   public Class_0995(FakeLagModule var1) {
      super();
      this.field_3083 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3083.field_3050.method_194();
   }
}
