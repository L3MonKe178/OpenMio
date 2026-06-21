package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0842;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0165 implements Predicate {
   public LogoutSpotsModule field_482;

   public Class_0165(LogoutSpotsModule var1) {
      super();
      this.field_482 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_482.field_2323.getValue() == Class_0842.SIMPLE || this.field_482.field_2323.getValue() == Class_0842.BOTH;
   }
}
