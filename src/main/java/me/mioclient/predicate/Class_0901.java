package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoTameModule;

public class Class_0901 implements Predicate {
   public AutoTameModule field_2831;

   public Class_0901(AutoTameModule var1) {
      super();
      this.field_2831 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2831.field_1362.method_194();
   }
}
