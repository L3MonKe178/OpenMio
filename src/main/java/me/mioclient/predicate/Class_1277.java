package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_1277 implements Predicate {
   public ElytraFlyModule field_4046;

   public Class_1277(ElytraFlyModule var1) {
      super();
      this.field_4046 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4046.field_4349.getValue() == Class_1229.PACKET;
   }
}
