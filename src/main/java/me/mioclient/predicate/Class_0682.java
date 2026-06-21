package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.CrosshairModule;

public class Class_0682 implements Predicate {
   public CrosshairModule field_2180;

   public Class_0682(CrosshairModule var1) {
      super();
      this.field_2180 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2180.field_3505.method_194();
   }
}
