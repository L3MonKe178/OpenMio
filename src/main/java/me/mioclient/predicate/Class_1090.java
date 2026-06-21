package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.AntiAFKModule;

public class Class_1090 implements Predicate {
   public AntiAFKModule field_3400;

   public Class_1090(AntiAFKModule var1) {
      super();
      this.field_3400 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3400.field_3237.getValue();
   }
}
