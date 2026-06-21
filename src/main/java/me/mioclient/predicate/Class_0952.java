package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0403;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0952 implements Predicate {
   public SpeedMineModule field_2954;

   public Class_0952(SpeedMineModule var1) {
      super();
      this.field_2954 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2954.field_4000.getValue() == Class_0403.NORMAL;
   }
}
