package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.StrafeType;
import me.mioclient.module.movement.SpeedModule;

public class Class_0810 implements Predicate {
   public SpeedModule field_2561;

   public Class_0810(SpeedModule var1) {
      super();
      this.field_2561 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2561.field_2191.getValue() != StrafeType.STRAFE_STRICT && this.field_2561.field_2191.getValue() != StrafeType.GRIM;
   }
}
