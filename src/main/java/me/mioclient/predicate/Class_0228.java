package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0616;
import me.mioclient.module.misc.AntiAimModule;

public class Class_0228 implements Predicate {
   public AntiAimModule field_624;

   public Class_0228(AntiAimModule var1) {
      super();
      this.field_624 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_624.field_2571.getValue() == Class_0616.JITTER;
   }
}
