package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.StrafeType;
import me.mioclient.module.movement.SpeedModule;

public class Class_0976 implements Predicate {
   public SpeedModule field_2996;

   public Class_0976(SpeedModule var1) {
      super();
      this.field_2996 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2996.field_2191.getValue() != StrafeType.GRIM;
   }
}
