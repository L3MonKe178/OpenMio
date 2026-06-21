package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoFarmModule;

public class Class_0811 implements Predicate {
   public AutoFarmModule field_2562;

   public Class_0811(AutoFarmModule var1) {
      super();
      this.field_2562 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2562.field_2254.method_194();
   }
}
