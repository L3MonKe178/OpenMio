package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.TrailsModule;

public class Class_0572 implements Predicate {
   public TrailsModule field_1805;

   public Class_0572(TrailsModule var1) {
      super();
      this.field_1805 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1805.field_3549.method_194();
   }
}
