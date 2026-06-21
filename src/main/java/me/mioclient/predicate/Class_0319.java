package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0285;
import me.mioclient.module.combat.AutoCrystalModule;

public class Class_0319 implements Predicate {
   public AutoCrystalModule field_1085;

   public Class_0319(AutoCrystalModule var1) {
      super();
      this.field_1085 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1085.field_4113.getValue() == Class_0285.SILENT;
   }
}
