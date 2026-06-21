package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0842;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0172 implements Predicate {
   public LogoutSpotsModule field_492;

   public Class_0172(LogoutSpotsModule var1) {
      super();
      this.field_492 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_492.field_2323.getValue() != Class_0842.NONE;
   }
}
