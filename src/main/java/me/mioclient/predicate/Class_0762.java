package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0762 implements Predicate {
   public LogoutSpotsModule field_2402;

   public Class_0762(LogoutSpotsModule var1) {
      super();
      this.field_2402 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2402.field_2329.method_194();
   }
}
