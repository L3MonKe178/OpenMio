package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0409 implements Predicate {
   public LogoutSpotsModule field_1316;

   public Class_0409(LogoutSpotsModule var1) {
      super();
      this.field_1316 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1316.field_2329.method_194();
   }
}
