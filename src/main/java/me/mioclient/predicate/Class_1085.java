package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoFarmModule;

public class Class_1085 implements Predicate {
   public AutoFarmModule field_3333;

   public Class_1085(AutoFarmModule var1) {
      super();
      this.field_3333 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3333.field_2254.method_194();
   }
}
