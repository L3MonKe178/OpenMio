package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0616;
import me.mioclient.module.misc.AntiAimModule;

public class Class_1077 implements Predicate {
   public AntiAimModule field_3297;

   public Class_1077(AntiAimModule var1) {
      super();
      this.field_3297 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3297.field_2571.getValue() == Class_0616.SPIN;
   }
}
