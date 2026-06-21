package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0616;
import me.mioclient.module.misc.AntiAimModule;

public class Class_0935 implements Predicate {
   public AntiAimModule field_2921;

   public Class_0935(AntiAimModule var1) {
      super();
      this.field_2921 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2921.field_2571.getValue() == Class_0616.STATIC;
   }
}
