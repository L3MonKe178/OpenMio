package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HitmarkerModule;

public class Class_0521 implements Predicate {
   public HitmarkerModule field_1655;

   public Class_0521(HitmarkerModule var1) {
      super();
      this.field_1655 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1655.field_1595.method_194();
   }
}
