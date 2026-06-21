package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HitmarkerModule;

public class Class_0984 implements Predicate {
   public HitmarkerModule field_3030;

   public Class_0984(HitmarkerModule var1) {
      super();
      this.field_3030 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3030.field_1604.method_194();
   }
}
