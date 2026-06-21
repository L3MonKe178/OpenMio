package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HitmarkerModule;

public class Class_0740 implements Predicate {
   public HitmarkerModule field_2359;

   public Class_0740(HitmarkerModule var1) {
      super();
      this.field_2359 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2359.field_1595.method_194();
   }
}
