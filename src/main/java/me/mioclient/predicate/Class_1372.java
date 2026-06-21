package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_1372 implements Predicate {
   public ElytraFlyModule field_4457;

   public Class_1372(ElytraFlyModule var1) {
      super();
      this.field_4457 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4457.field_4349.getValue() == Class_1229.BOOST && this.field_4457.field_4358.method_194();
   }
}
