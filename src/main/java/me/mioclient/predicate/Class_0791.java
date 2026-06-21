package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HitmarkerModule;

public class Class_0791 implements Predicate {
   public HitmarkerModule field_2487;

   public Class_0791(HitmarkerModule var1) {
      super();
      this.field_2487 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2487.field_1601.method_194();
   }
}
