package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.KillEffectsModule;

public class Class_0575 implements Predicate {
   public KillEffectsModule field_1814;

   public Class_0575(KillEffectsModule var1) {
      super();
      this.field_1814 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1814.field_895.method_194();
   }
}
