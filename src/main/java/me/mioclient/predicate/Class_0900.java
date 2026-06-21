package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0900 implements Predicate {
   public SpeedMineModule field_2830;

   public Class_0900(SpeedMineModule var1) {
      super();
      this.field_2830 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2830.field_4005.method_194();
   }
}
