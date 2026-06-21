package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HitmarkerModule;

public class Class_0545 implements Predicate {
   public HitmarkerModule field_1747;

   public Class_0545(HitmarkerModule var1) {
      super();
      this.field_1747 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1747.field_1604.method_194();
   }
}
