package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0692;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0120 implements Predicate {
   public SpeedMineModule field_356;

   public Class_0120(SpeedMineModule var1) {
      super();
      this.field_356 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_356.field_3994.getValue() == Class_0692.INSTANT && this.field_356.field_3993.method_194();
   }
}
