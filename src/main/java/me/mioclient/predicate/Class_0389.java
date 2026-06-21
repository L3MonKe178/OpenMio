package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0614;
import me.mioclient.module.movement.NoSlowModule;

public class Class_0389 implements Predicate {
   public NoSlowModule field_1263;

   public Class_0389(NoSlowModule var1) {
      super();
      this.field_1263 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1263.field_1691.getValue() != Class_0614.GRIM && this.field_1263.field_1691.getValue() != Class_0614.GRIMV3;
   }
}
