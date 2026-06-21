package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0761 implements Predicate {
   public LogoutSpotsModule field_2401;

   public Class_0761(LogoutSpotsModule var1) {
      super();
      this.field_2401 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2401.field_2340.method_194();
   }
}
