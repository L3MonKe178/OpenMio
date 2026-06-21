package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.enum_.Class_0285;
import me.mioclient.enum_.Class_1140;
import me.mioclient.module.combat.AutoCrystalModule;

public class Class_1155 implements Predicate {
   public AutoCrystalModule field_3563;

   public Class_1155(AutoCrystalModule var1) {
      super();
      this.field_3563 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3563.field_4113.getValue() == Class_0285.SILENT && this.field_3563.field_4114.getValue() == Class_1140.ALTERNATIVE;
   }
}
