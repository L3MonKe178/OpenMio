package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.StrafeType;
import me.mioclient.module.movement.SpeedModule;

public class Class_0492 implements Predicate {
   public SpeedModule field_1558;

   public Class_0492(SpeedModule var1) {
      super();
      this.field_1558 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1558.field_2191.getValue() != StrafeType.GRIM;
   }
}
