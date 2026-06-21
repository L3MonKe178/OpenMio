package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.TrailsModule;

public class Class_0623 implements Predicate {
   public TrailsModule field_1989;

   public Class_0623(TrailsModule var1) {
      super();
      this.field_1989 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1989.field_3556.method_194();
   }
}
