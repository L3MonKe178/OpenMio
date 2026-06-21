package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0403;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0880 implements Predicate {
   public SpeedMineModule field_2789;

   public Class_0880(SpeedMineModule var1) {
      super();
      this.field_2789 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2789.field_4000.getValue() == Class_0403.SILENT && this.field_2789.field_4003.getValue() && this.field_2789.field_3999.method_194();
   }
}
