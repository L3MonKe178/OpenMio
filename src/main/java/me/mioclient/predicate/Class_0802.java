package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HitmarkerModule;

public class Class_0802 implements Predicate {
   public HitmarkerModule field_2531;

   public Class_0802(HitmarkerModule var1) {
      super();
      this.field_2531 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2531.field_1595.method_194();
   }
}
