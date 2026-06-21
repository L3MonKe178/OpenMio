package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1360;
import me.mioclient.module.movement.AntiVoidModule;

public class Class_0870 implements Predicate {
   public AntiVoidModule field_2777;

   public Class_0870(AntiVoidModule var1) {
      super();
      this.field_2777 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2777.field_127.getValue() == Class_1360.CANCEL;
   }
}
