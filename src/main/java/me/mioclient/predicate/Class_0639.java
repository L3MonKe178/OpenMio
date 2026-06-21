package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1269;
import me.mioclient.module.movement.WarpModule;

public class Class_0639 implements Predicate {
   public WarpModule field_2012;

   public Class_0639(WarpModule var1) {
      super();
      this.field_2012 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2012.field_1108.getValue() == Class_1269.PLAIN;
   }
}
