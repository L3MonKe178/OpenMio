package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0184;
import me.mioclient.module.movement.VelocityModule;

public class Class_1278 implements Predicate {
   public VelocityModule field_4047;

   public Class_1278(VelocityModule var1) {
      super();
      this.field_4047 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4047.field_2509.getValue() == Class_0184.PLAIN;
   }
}
