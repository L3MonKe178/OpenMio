package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_1324 implements Predicate {
   public ElytraFlyModule field_4277;

   public Class_1324(ElytraFlyModule var1) {
      super();
      this.field_4277 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4277.field_4349.getValue() == Class_1229.BOOST && this.field_4277.field_4360.method_194();
   }
}
