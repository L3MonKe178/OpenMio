package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.StrafeType;
import me.mioclient.module.movement.SpeedModule;

public class Class_1093 implements Predicate {
   public SpeedModule field_3402;

   public Class_1093(SpeedModule var1) {
      super();
      this.field_3402 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3402.field_2191.getValue() == StrafeType.STRAFE_STRICT || this.field_3402.field_2191.getValue() == StrafeType.STRAFE;
   }
}
