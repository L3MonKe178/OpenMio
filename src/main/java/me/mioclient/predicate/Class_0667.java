package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AuraModule;

public class Class_0667 implements Predicate {
   public AuraModule field_2143;

   public Class_0667(AuraModule var1) {
      super();
      this.field_2143 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2143.field_1051.method_194();
   }
}
