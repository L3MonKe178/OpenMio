package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.SpeedMineModule;

public class Class_1336 implements Predicate {
   public SpeedMineModule field_4315;

   public Class_1336(SpeedMineModule var1) {
      super();
      this.field_4315 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4315.field_4007.method_194();
   }
}
