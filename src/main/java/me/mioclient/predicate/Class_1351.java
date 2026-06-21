package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_1351 implements Predicate {
   public LogoutSpotsModule field_4413;

   public Class_1351(LogoutSpotsModule var1) {
      super();
      this.field_4413 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4413.field_2329.method_194();
   }
}
