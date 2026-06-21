package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.TrailsModule;

public class Class_1284 implements Predicate {
   public TrailsModule field_4159;

   public Class_1284(TrailsModule var1) {
      super();
      this.field_4159 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4159.field_3549.method_194();
   }
}
