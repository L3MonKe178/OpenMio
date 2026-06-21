package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_0083 implements Predicate {
   public ElytraFlyModule field_283;

   public Class_0083(ElytraFlyModule var1) {
      super();
      this.field_283 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_283.field_4349.getValue() == Class_1229.BOOST && ElytraFlyModule.field_4348.method_1052();
   }
}
