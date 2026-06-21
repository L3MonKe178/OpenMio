package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0885;
import me.mioclient.module.combat.AuraModule;

public class Class_0176 implements Predicate {
   public AuraModule field_500;

   public Class_0176(AuraModule var1) {
      super();
      this.field_500 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_500.field_1058.method_176()
         && (this.field_500.field_1058.getValue() == Class_0885.DENSITY || this.field_500.field_1058.getValue() == Class_0885.SMART);
   }
}
