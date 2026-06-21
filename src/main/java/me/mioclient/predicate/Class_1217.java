package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.ChamsModule;

public class Class_1217 implements Predicate {
   public ChamsModule field_3807;

   public Class_1217(ChamsModule var1) {
      super();
      this.field_3807 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3807.field_230.method_105();
   }
}
