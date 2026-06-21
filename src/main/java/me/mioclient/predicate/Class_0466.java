package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0753;
import me.mioclient.module.movement.FastFallModule;

public class Class_0466 implements Predicate {
   public FastFallModule field_1474;

   public Class_0466(FastFallModule var1) {
      super();
      this.field_1474 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1474.field_4400.getValue() == Class_0753.PLAIN;
   }
}
