package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HitmarkerModule;

public class Class_0817 implements Predicate {
   public HitmarkerModule field_2592;

   public Class_0817(HitmarkerModule var1) {
      super();
      this.field_2592 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2592.field_1595.method_194();
   }
}
