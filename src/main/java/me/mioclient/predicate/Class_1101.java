package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.SelfWebModule;

public class Class_1101 implements Predicate {
   public SelfWebModule field_3413;

   public Class_1101(SelfWebModule var1) {
      super();
      this.field_3413 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3413.field_489.method_194();
   }
}
