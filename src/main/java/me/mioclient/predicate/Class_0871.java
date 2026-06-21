package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AuraModule;

public class Class_0871 implements Predicate {
   public AuraModule field_2778;

   public Class_0871(AuraModule var1) {
      super();
      this.field_2778 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2778.field_1061.method_194();
   }
}
