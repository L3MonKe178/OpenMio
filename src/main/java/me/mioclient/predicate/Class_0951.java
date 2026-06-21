package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0285;
import me.mioclient.module.combat.AutoCrystalModule;

public class Class_0951 implements Predicate {
   public AutoCrystalModule field_2953;

   public Class_0951(AutoCrystalModule var1) {
      super();
      this.field_2953 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2953.field_4113.getValue() == Class_0285.NORMAL;
   }
}
