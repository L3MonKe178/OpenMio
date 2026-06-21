package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.FakeLagModule;

public class Class_1181 implements Predicate {
   public FakeLagModule field_3650;

   public Class_1181(FakeLagModule var1) {
      super();
      this.field_3650 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3650.field_3050.method_194();
   }
}
