package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_0405 implements Predicate {
   public ElytraFlyModule field_1305;

   public Class_0405(ElytraFlyModule var1) {
      super();
      this.field_1305 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1305.field_4349.getValue() == Class_1229.PACKET || this.field_1305.field_4349.getValue() == Class_1229.CONTROL;
   }
}
