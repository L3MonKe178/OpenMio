package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0525;
import me.mioclient.module.movement.JesusModule;

public class Class_0583 implements Predicate {
   public JesusModule field_1845;

   public Class_0583(JesusModule var1) {
      super();
      this.field_1845 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1845.field_1268.getValue() == Class_0525.SOLID;
   }
}
