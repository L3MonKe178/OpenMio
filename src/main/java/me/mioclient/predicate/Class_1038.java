package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AutoArmorModule;

public class Class_1038 implements Predicate {
   public AutoArmorModule field_3218;

   public Class_1038(AutoArmorModule var1) {
      super();
      this.field_3218 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3218.field_2931.method_194();
   }
}
