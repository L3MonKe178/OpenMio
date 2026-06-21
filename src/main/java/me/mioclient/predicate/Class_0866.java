package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.KillEffectsModule;

public class Class_0866 implements Predicate {
   public KillEffectsModule field_2776;

   public Class_0866(KillEffectsModule var1) {
      super();
      this.field_2776 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2776.field_886.method_194();
   }
}
