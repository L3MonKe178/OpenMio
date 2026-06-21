package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0525;
import me.mioclient.module.movement.JesusModule;

public class Class_1076 implements Predicate {
   public JesusModule field_3296;

   public Class_1076(JesusModule var1) {
      super();
      this.field_3296 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3296.field_1268.getValue() == Class_0525.SOLID;
   }
}
