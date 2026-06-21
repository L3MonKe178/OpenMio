package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0001;
import me.mioclient.module.combat.AuraModule;

public class Class_0590 implements Predicate {
   public AuraModule field_1854;

   public Class_0590(AuraModule var1) {
      super();
      this.field_1854 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1854.field_1057.getValue() == Class_0001.SILENT;
   }
}
