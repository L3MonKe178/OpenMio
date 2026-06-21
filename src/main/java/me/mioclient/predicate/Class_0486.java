package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AuraModule;

public class Class_0486 implements Predicate {
   public AuraModule field_1554;

   public Class_0486(AuraModule var1) {
      super();
      this.field_1554 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1554.field_1061.method_194() && this.field_1554.field_1062.method_194();
   }
}
