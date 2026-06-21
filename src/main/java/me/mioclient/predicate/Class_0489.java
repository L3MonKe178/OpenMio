package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AntiPhaseModule;

public class Class_0489 implements Predicate {
   public AntiPhaseModule field_1556;

   public Class_0489(AntiPhaseModule var1) {
      super();
      this.field_1556 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1556.field_851.method_194();
   }
}
