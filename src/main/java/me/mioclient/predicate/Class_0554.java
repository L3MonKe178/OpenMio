package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0374;
import me.mioclient.module.exploit.TimerModule;

public class Class_0554 implements Predicate {
   public TimerModule field_1762;

   public Class_0554(TimerModule var1) {
      super();
      this.field_1762 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1762.field_1780.getValue() == Class_0374.PULSE;
   }
}
