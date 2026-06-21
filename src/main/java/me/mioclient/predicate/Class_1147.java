package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.SpeedMineModule;

public class Class_1147 implements Predicate {
   public SpeedMineModule field_3542;

   public Class_1147(SpeedMineModule var1) {
      super();
      this.field_3542 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3542.field_3993.method_194();
   }
}
