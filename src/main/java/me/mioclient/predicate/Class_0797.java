package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0717;
import me.mioclient.module.misc.AntiAimModule;

public class Class_0797 implements Predicate {
   public AntiAimModule field_2518;

   public Class_0797(AntiAimModule var1) {
      super();
      this.field_2518 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2518.field_2578.getValue() == Class_0717.STATIC;
   }
}
