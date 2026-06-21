package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.CrosshairModule;

public class Class_0730 implements Predicate {
   public CrosshairModule field_2317;

   public Class_0730(CrosshairModule var1) {
      super();
      this.field_2317 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2317.field_3505.method_194();
   }
}
