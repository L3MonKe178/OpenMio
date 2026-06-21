package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0842;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_1358 implements Predicate {
   public LogoutSpotsModule field_4426;

   public Class_1358(LogoutSpotsModule var1) {
      super();
      this.field_4426 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4426.field_2323.getValue() == Class_0842.SIMPLE || this.field_4426.field_2323.getValue() == Class_0842.BOTH;
   }
}
