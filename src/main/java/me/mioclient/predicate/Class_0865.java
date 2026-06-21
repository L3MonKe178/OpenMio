package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0865 implements Predicate {
   public LogoutSpotsModule field_2775;

   public Class_0865(LogoutSpotsModule var1) {
      super();
      this.field_2775 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2775.field_2329.method_194();
   }
}
