package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.NoSlowModule;

public class Class_0701 implements Predicate {
   public NoSlowModule field_2225;

   public Class_0701(NoSlowModule var1) {
      super();
      this.field_2225 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2225.field_1694.method_194();
   }
}
