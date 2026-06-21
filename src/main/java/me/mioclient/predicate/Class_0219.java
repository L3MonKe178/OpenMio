package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.HoleESPModule;

public class Class_0219 implements Predicate {
   public HoleESPModule field_612;

   public Class_0219(HoleESPModule var1) {
      super();
      this.field_612 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_612.field_4326.method_194();
   }
}
