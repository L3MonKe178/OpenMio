package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.CrosshairModule;

public class Class_0644 implements Predicate {
   public CrosshairModule field_2061;

   public Class_0644(CrosshairModule var1) {
      super();
      this.field_2061 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2061.field_3503.method_194();
   }
}
