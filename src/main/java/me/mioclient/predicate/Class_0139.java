package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.misc.KillEffectsModule;

public class Class_0139 implements Predicate {
   public KillEffectsModule field_413;

   public Class_0139(KillEffectsModule var1) {
      super();
      this.field_413 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_413.field_893.method_194();
   }
}
