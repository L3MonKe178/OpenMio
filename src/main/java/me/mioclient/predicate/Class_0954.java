package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.Hub;
import me.mioclient.module.combat.AuraModule;

public class Class_0954 implements Predicate {
   public AuraModule field_2956;

   public Class_0954(AuraModule var1) {
      super();
      this.field_2956 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return Hub.field_2630.method_264();
   }
}
