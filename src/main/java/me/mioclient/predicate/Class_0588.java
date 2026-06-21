package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0195;
import me.mioclient.module.player.AutoFarmModule;

public class Class_0588 implements Predicate {
   public AutoFarmModule field_1850;

   public Class_0588(AutoFarmModule var1) {
      super();
      this.field_1850 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1850.field_2245.getValue() == Class_0195.SPHERE;
   }
}
