package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0137;
import me.mioclient.module.movement.NoFallModule;

public class Class_0122 implements Predicate {
   public NoFallModule field_361;

   public Class_0122(NoFallModule var1) {
      super();
      this.field_361 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_361.field_4157.getValue() == Class_0137.TELEPORT || this.field_361.field_4157.getValue() == Class_0137.MLG;
   }
}
