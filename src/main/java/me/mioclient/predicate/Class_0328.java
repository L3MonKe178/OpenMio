package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0049;
import me.mioclient.module.exploit.PhaseModule;

public class Class_0328 implements Predicate {
   public PhaseModule field_1102;

   public Class_0328(PhaseModule var1) {
      super();
      this.field_1102 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1102.field_1004.getValue() == Class_0049.PEARL;
   }
}
