package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AuraModule;

public class Class_0148 implements Predicate {
   public AuraModule field_429;

   public Class_0148(AuraModule var1) {
      super();
      this.field_429 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_429.field_1061.method_194();
   }
}
