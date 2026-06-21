package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.TrailsModule;

public class Class_0463 implements Predicate {
   public TrailsModule field_1469;

   public Class_0463(TrailsModule var1) {
      super();
      this.field_1469 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1469.field_3556.method_194();
   }
}
