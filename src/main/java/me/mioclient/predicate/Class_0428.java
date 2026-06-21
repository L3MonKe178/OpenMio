package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0842;
import me.mioclient.module.render.LogoutSpotsModule;

public class Class_0428 implements Predicate {
   public LogoutSpotsModule field_1353;

   public Class_0428(LogoutSpotsModule var1) {
      super();
      this.field_1353 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1353.field_2323.getValue() == Class_0842.COMPLEX || this.field_1353.field_2323.getValue() == Class_0842.BOTH;
   }
}
