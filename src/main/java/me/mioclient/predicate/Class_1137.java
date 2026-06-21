package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AuraModule;

public class Class_1137 implements Predicate {
   public AuraModule field_3522;

   public Class_1137(AuraModule var1) {
      super();
      this.field_3522 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3522.field_1061.method_194();
   }
}
