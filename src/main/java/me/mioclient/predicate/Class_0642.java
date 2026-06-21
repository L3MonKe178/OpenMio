package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0642 implements Predicate {
   public LogoutSpotsModule field_2057;

   public Class_0642(LogoutSpotsModule var1) {
      super();
      this.field_2057 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2057.field_2340.method_194();
   }
}
