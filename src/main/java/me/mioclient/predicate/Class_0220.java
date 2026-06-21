package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0446;
import me.mioclient.module.combat.AutoCrystalModule;

public class Class_0220 implements Predicate {
   public AutoCrystalModule field_613;

   public Class_0220(AutoCrystalModule var1) {
      super();
      this.field_613 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_613.field_4077.getValue() != Class_0446.NONE;
   }
}
