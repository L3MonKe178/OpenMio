package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoTameModule;

public class Class_0759 implements Predicate {
   public AutoTameModule field_2395;

   public Class_0759(AutoTameModule var1) {
      super();
      this.field_2395 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2395.field_1362.method_194();
   }
}
