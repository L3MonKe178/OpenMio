package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AutoArmorModule;

public class Class_0778 implements Predicate {
   public AutoArmorModule field_2439;

   public Class_0778(AutoArmorModule var1) {
      super();
      this.field_2439 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2439.field_2933.method_194();
   }
}
