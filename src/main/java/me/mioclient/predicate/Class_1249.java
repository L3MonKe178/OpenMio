package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HoleESPModule;

public class Class_1249 implements Predicate {
   public HoleESPModule field_3916;

   public Class_1249(HoleESPModule var1) {
      super();
      this.field_3916 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3916.field_4321.method_194();
   }
}
