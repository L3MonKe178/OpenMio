package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.AutoTameModule;

public class Class_1020 implements Predicate {
   public AutoTameModule field_3172;

   public Class_1020(AutoTameModule var1) {
      super();
      this.field_3172 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3172.field_1362.method_194();
   }
}
