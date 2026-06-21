package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0842;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_1329 implements Predicate {
   public LogoutSpotsModule field_4295;

   public Class_1329(LogoutSpotsModule var1) {
      super();
      this.field_4295 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4295.field_2323.getValue() == Class_0842.COMPLEX || this.field_4295.field_2323.getValue() == Class_0842.BOTH;
   }
}
