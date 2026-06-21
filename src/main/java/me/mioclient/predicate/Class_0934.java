package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0374;
import me.mioclient.module.exploit.TimerModule;

public class Class_0934 implements Predicate {
   public TimerModule field_2920;

   public Class_0934(TimerModule var1) {
      super();
      this.field_2920 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2920.field_1780.getValue() == Class_0374.PULSE;
   }
}
