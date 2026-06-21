package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_0728 implements Predicate {
   public ElytraFlyModule field_2316;

   public Class_0728(ElytraFlyModule var1) {
      super();
      this.field_2316 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2316.field_4349.getValue() == Class_1229.BOOST && this.field_2316.field_4360.method_194();
   }
}
