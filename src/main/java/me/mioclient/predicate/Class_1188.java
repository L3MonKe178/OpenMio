package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.KillEffectsModule;

public class Class_1188 implements Predicate {
   public KillEffectsModule field_3674;

   public Class_1188(KillEffectsModule var1) {
      super();
      this.field_3674 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3674.field_886.method_194() && this.field_3674.field_887.method_194();
   }
}
