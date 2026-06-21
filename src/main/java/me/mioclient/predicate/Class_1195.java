package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0403;
import me.mioclient.module.player.SpeedMineModule;

public class Class_1195 implements Predicate {
   public SpeedMineModule field_3679;

   public Class_1195(SpeedMineModule var1) {
      super();
      this.field_3679 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3679.field_4000.getValue() == Class_0403.SILENT && this.field_3679.field_3999.method_194();
   }
}
