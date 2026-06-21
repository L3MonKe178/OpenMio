package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoFarmModule;

public class Class_1159 implements Predicate {
   public AutoFarmModule field_3596;

   public Class_1159(AutoFarmModule var1) {
      super();
      this.field_3596 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3596.field_2254.method_194();
   }
}
