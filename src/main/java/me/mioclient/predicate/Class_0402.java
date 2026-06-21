package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.KillEffectsModule;

public class Class_0402 implements Predicate {
   public KillEffectsModule field_1295;

   public Class_0402(KillEffectsModule var1) {
      super();
      this.field_1295 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1295.field_886.method_194() && this.field_1295.field_890.method_194();
   }
}
