package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0459 implements Predicate {
   public SpeedMineModule field_1465;

   public Class_0459(SpeedMineModule var1) {
      super();
      this.field_1465 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1465.field_3999.method_194();
   }
}
