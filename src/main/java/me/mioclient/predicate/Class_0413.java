package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HitmarkerModule;

public class Class_0413 implements Predicate {
   public HitmarkerModule field_1317;

   public Class_0413(HitmarkerModule var1) {
      super();
      this.field_1317 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1317.field_1604.method_194();
   }
}
