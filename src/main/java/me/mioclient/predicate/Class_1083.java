package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HoleESPModule;

public class Class_1083 implements Predicate {
   public HoleESPModule field_3331;

   public Class_1083(HoleESPModule var1) {
      super();
      this.field_3331 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_3331.field_4323.method_194();
   }
}
