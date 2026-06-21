package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0616;
import me.mioclient.module.misc.AntiAimModule;

public class Class_0379 implements Predicate {
   public AntiAimModule field_1226;

   public Class_0379(AntiAimModule var1) {
      super();
      this.field_1226 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1226.field_2571.getValue() == Class_0616.FLIP;
   }
}
