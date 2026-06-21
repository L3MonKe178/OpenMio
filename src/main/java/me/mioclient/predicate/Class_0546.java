package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0692;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0546 implements Predicate {
   public SpeedMineModule field_1748;

   public Class_0546(SpeedMineModule var1) {
      super();
      this.field_1748 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1748.field_3994.getValue() == Class_0692.INSTANT && this.field_1748.field_3993.method_194();
   }
}
