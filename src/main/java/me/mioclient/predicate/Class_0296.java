package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HoleESPModule;

public class Class_0296 implements Predicate {
   public HoleESPModule field_949;

   public Class_0296(HoleESPModule var1) {
      super();
      this.field_949 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_949.field_4329.method_194();
   }
}
