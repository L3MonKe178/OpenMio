package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoFarmModule;

public class Class_0503 implements Predicate {
   public AutoFarmModule field_1581;

   public Class_0503(AutoFarmModule var1) {
      super();
      this.field_1581 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1581.field_2251.method_194();
   }
}
