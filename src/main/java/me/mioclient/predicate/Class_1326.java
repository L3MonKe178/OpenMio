package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AutoArmorModule;

public class Class_1326 implements Predicate {
   public AutoArmorModule field_4280;

   public Class_1326(AutoArmorModule var1) {
      super();
      this.field_4280 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4280.field_2933.method_194();
   }
}
