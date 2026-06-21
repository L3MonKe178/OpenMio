package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.movement.FlightModule;

public class Class_1063 implements Predicate {
   public FlightModule field_3271;

   public Class_1063(FlightModule var1) {
      super();
      this.field_3271 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3271.field_3489.method_194();
   }
}
