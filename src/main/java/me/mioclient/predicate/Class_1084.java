package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_1229;
import me.mioclient.module.movement.ElytraFlyModule;

public class Class_1084 implements Predicate {
   public ElytraFlyModule field_3332;

   public Class_1084(ElytraFlyModule var1) {
      super();
      this.field_3332 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3332.field_4349.getValue() == Class_1229.CONTROL && this.field_3332.field_4370.method_194();
   }
}
