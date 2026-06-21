package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0374;
import me.mioclient.module.exploit.TimerModule;

public class Class_0683 implements Predicate {
   public TimerModule field_2181;

   public Class_0683(TimerModule var1) {
      super();
      this.field_2181 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2181.field_1780.getValue() == Class_0374.PULSE;
   }
}
