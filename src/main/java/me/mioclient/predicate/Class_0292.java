package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0845;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_0292 implements Predicate {
   public ElytraFlyModule field_945;

   public Class_0292(ElytraFlyModule var1) {
      super();
      this.field_945 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_945.field_4365.getValue() == Class_0845.STRICT && this.field_945.field_4349.getValue() == Class_1229.CONTROL;
   }
}
