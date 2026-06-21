package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0845;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_1014 implements Predicate {
   public ElytraFlyModule field_3136;

   public Class_1014(ElytraFlyModule var1) {
      super();
      this.field_3136 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3136.field_4365.getValue() == Class_0845.PLAIN && this.field_3136.field_4349.getValue() == Class_1229.CONTROL;
   }
}
