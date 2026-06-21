package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.TotemType;
import me.mioclient.module.combat.OffhandModule;

public class Class_0324 implements Predicate {
   public OffhandModule field_1093;

   public Class_0324(OffhandModule var1) {
      super();
      this.field_1093 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1093.field_1962.getValue() == TotemType.GAPPLE || this.field_1093.field_1968.method_194();
   }
}
