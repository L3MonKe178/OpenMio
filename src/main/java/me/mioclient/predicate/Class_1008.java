package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1379;
import me.mioclient.module.misc.CustomDeathTextModule;

public class Class_1008 implements Predicate {
   public CustomDeathTextModule field_3109;

   public Class_1008(CustomDeathTextModule var1) {
      super();
      this.field_3109 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3109.field_493.getValue() == Class_1379.CUSTOM;
   }
}
