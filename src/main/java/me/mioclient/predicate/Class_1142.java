package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoFarmModule;

public class Class_1142 implements Predicate {
   public AutoFarmModule field_3538;

   public Class_1142(AutoFarmModule var1) {
      super();
      this.field_3538 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3538.field_2251.method_194();
   }
}
