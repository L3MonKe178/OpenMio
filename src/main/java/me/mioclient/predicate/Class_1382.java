package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0374;
import me.mioclient.module.exploit.TimerModule;

public class Class_1382 implements Predicate {
   public TimerModule field_4488;

   public Class_1382(TimerModule var1) {
      super();
      this.field_4488 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4488.field_1780.getValue() == Class_0374.PULSE;
   }
}
