package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0174;
import me.mioclient.module.player.SpeedMineModule;

public class Class_0938 implements Predicate {
   public SpeedMineModule field_2941;

   public Class_0938(SpeedMineModule var1) {
      super();
      this.field_2941 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2941.field_4007.method_194() && this.field_2941.field_4012.getValue() == Class_0174.CUSTOM;
   }
}
