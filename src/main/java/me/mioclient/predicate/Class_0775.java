package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AntiPhaseModule;

public class Class_0775 implements Predicate {
   public AntiPhaseModule field_2433;

   public Class_0775(AntiPhaseModule var1) {
      super();
      this.field_2433 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2433.field_851.method_194();
   }
}
