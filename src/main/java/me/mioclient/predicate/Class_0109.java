package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1269;
import me.mioclient.module.movement.WarpModule;

public class Class_0109 implements Predicate {
   public WarpModule field_333;

   public Class_0109(WarpModule var1) {
      super();
      this.field_333 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_333.field_1108.getValue() == Class_1269.PLAIN;
   }
}
