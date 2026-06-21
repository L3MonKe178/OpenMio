package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0993 implements Predicate {
   public LogoutSpotsModule field_3079;

   public Class_0993(LogoutSpotsModule var1) {
      super();
      this.field_3079 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3079.field_2327.method_194();
   }
}
