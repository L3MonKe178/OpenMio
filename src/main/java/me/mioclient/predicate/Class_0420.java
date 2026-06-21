package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_0420 implements Predicate {
   public ElytraFlyModule field_1334;

   public Class_0420(ElytraFlyModule var1) {
      super();
      this.field_1334 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1334.field_4349.getValue() == Class_1229.BOOST;
   }
}
