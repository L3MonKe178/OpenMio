package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0403;
import me.mioclient.module.player.SpeedMineModule;

public class Class_1270 implements Predicate {
   public SpeedMineModule field_4035;

   public Class_1270(SpeedMineModule var1) {
      super();
      this.field_4035 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4035.field_4000.getValue() == Class_0403.SILENT && this.field_4035.field_3999.method_194();
   }
}
