package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_1148 implements Predicate {
   public ElytraFlyModule field_3543;

   public Class_1148(ElytraFlyModule var1) {
      super();
      this.field_3543 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3543.field_4349.getValue() == Class_1229.CONTROL;
   }
}
