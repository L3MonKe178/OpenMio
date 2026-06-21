package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.StrafeType;
import me.mioclient.module.movement.SpeedModule;

public class Class_1179 implements Predicate {
   public SpeedModule field_3648;

   public Class_1179(SpeedModule var1) {
      super();
      this.field_3648 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3648.field_2191.getValue() != StrafeType.GRIM;
   }
}
