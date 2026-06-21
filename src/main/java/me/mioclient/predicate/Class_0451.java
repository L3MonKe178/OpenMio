package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.TrailsModule;

public class Class_0451 implements Predicate {
   public TrailsModule field_1450;

   public Class_0451(TrailsModule var1) {
      super();
      this.field_1450 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1450.field_3549.method_194();
   }
}
