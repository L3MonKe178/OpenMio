package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.combat.AutoLogModule;

public class Class_0383 implements Predicate {
   public AutoLogModule field_1230;

   public Class_0383(AutoLogModule var1) {
      super();
      this.field_1230 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1230.field_198.method_194();
   }
}
