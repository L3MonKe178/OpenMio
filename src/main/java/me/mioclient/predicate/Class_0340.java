package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0340 implements Predicate {
   public SpeedMineModule field_1135;

   public Class_0340(SpeedMineModule var1) {
      super();
      this.field_1135 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1135.field_4007.method_194();
   }
}
