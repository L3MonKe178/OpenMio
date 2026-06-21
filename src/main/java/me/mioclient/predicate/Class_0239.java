package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AuraModule;

public class Class_0239 implements Predicate {
   public AuraModule field_652;

   public Class_0239(AuraModule var1) {
      super();
      this.field_652 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_652.field_1061.method_194();
   }
}
