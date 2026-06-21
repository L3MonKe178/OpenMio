package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_0955 implements Predicate {
   public ElytraFlyModule field_2957;

   public Class_0955(ElytraFlyModule var1) {
      super();
      this.field_2957 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2957.field_4349.getValue() == Class_1229.PACKET || this.field_2957.field_4349.getValue() == Class_1229.CONTROL;
   }
}
