package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.KillEffectsModule;

public class Class_0780 implements Predicate {
   public KillEffectsModule field_2440;

   public Class_0780(KillEffectsModule var1) {
      super();
      this.field_2440 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2440.field_886.method_194() && this.field_2440.field_890.method_194();
   }
}
