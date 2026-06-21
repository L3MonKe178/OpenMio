package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HoleESPModule;

public class Class_0812 implements Predicate {
   public HoleESPModule field_2563;

   public Class_0812(HoleESPModule var1) {
      super();
      this.field_2563 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2563.field_4326.method_194();
   }
}
