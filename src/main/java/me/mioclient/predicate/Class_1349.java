package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HoleESPModule;

public class Class_1349 implements Predicate {
   public HoleESPModule field_4397;

   public Class_1349(HoleESPModule var1) {
      super();
      this.field_4397 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4397.field_4323.method_194();
   }
}
