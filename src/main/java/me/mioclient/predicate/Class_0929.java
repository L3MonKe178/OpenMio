package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0929 implements Predicate {
   public SpeedMineModule field_2912;

   public Class_0929(SpeedMineModule var1) {
      super();
      this.field_2912 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2912.field_3993.method_194();
   }
}
