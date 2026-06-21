package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AuraModule;

public class Class_1091 implements Predicate {
   public AuraModule field_3401;

   public Class_1091(AuraModule var1) {
      super();
      this.field_3401 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3401.field_1051.method_194();
   }
}
