package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0768;
import me.mioclient.module.combat.AuraModule;

public class Class_0697 implements Predicate {
   public AuraModule field_2212;

   public Class_0697(AuraModule var1) {
      super();
      this.field_2212 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2212.field_1056.getValue() == Class_0768.SWAP;
   }
}
