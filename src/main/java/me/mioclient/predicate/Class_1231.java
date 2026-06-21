package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0001;
import me.mioclient.enum_.Class_0768;
import me.mioclient.module.combat.AuraModule;

public class Class_1231 implements Predicate {
   public AuraModule field_3878;

   public Class_1231(AuraModule var1) {
      super();
      this.field_3878 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3878.field_1057.getValue() != Class_0001.SILENT || this.field_3878.field_1056.getValue() != Class_0768.SWAP;
   }
}
