package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoFarmModule;

public class Class_0825 implements Predicate {
   public AutoFarmModule field_2639;

   public Class_0825(AutoFarmModule var1) {
      super();
      this.field_2639 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2639.field_2254.method_194();
   }
}
