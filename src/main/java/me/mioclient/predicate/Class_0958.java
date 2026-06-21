package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AuraModule;

public class Class_0958 implements Predicate {
   public AuraModule field_2970;

   public Class_0958(AuraModule var1) {
      super();
      this.field_2970 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2970.field_1051.method_194();
   }
}
