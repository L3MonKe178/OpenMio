package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0692;
import me.mioclient.module.player.SpeedMineModule;

public class Class_1286 implements Predicate {
   public SpeedMineModule field_4165;

   public Class_1286(SpeedMineModule var1) {
      super();
      this.field_4165 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4165.field_4007.method_194() && this.field_4165.field_3994.getValue() == Class_0692.INSTANT;
   }
}
