package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0692;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0708 implements Predicate {
   public SpeedMineModule field_2239;

   public Class_0708(SpeedMineModule var1) {
      super();
      this.field_2239 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2239.field_4007.method_194() && this.field_2239.field_3994.getValue() == Class_0692.INSTANT && !this.field_2239.field_4010.getValue();
   }
}
