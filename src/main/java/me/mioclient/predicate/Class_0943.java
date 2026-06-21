package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.TrailsModule;

public class Class_0943 implements Predicate {
   public TrailsModule field_2947;

   public Class_0943(TrailsModule var1) {
      super();
      this.field_2947 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2947.field_3549.method_194();
   }
}
