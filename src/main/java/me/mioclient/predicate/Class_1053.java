package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.KillEffectsModule;

public class Class_1053 implements Predicate {
   public KillEffectsModule field_3249;

   public Class_1053(KillEffectsModule var1) {
      super();
      this.field_3249 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3249.field_886.method_194();
   }
}
