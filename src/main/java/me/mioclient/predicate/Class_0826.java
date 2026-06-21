package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0842;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0826 implements Predicate {
   public LogoutSpotsModule field_2640;

   public Class_0826(LogoutSpotsModule var1) {
      super();
      this.field_2640 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2640.field_2323.getValue() == Class_0842.COMPLEX || this.field_2640.field_2323.getValue() == Class_0842.BOTH;
   }
}
