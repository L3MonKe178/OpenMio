package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.TrailsModule;

public class Class_0342 implements Predicate {
   public TrailsModule field_1142;

   public Class_0342(TrailsModule var1) {
      super();
      this.field_1142 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1142.field_3549.method_194();
   }
}
