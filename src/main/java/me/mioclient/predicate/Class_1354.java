package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AuraModule;

public class Class_1354 implements Predicate {
   public AuraModule field_4415;

   public Class_1354(AuraModule var1) {
      super();
      this.field_4415 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4415.field_1061.method_194();
   }
}
