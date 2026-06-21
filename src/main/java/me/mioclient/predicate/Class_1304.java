package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.WaypointsModule;

public class Class_1304 implements Predicate {
   public WaypointsModule field_4216;

   public Class_1304(WaypointsModule var1) {
      super();
      this.field_4216 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4216.field_3310.getValue();
   }
}
