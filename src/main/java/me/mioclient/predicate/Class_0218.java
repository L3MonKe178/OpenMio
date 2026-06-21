package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0174;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0218 implements Predicate {
   public SpeedMineModule field_611;

   public Class_0218(SpeedMineModule var1) {
      super();
      this.field_611 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_611.field_4007.method_194() && this.field_611.field_4012.getValue() == Class_0174.CUSTOM;
   }
}
