package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0646 implements Predicate {
   public SpeedMineModule field_2063;

   public Class_0646(SpeedMineModule var1) {
      super();
      this.field_2063 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2063.field_4007.method_194();
   }
}
