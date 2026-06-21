package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.WaypointsModule;

public class Class_0907 implements Predicate {
   public WaypointsModule field_2842;

   public Class_0907(WaypointsModule var1) {
      super();
      this.field_2842 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_2842.field_3310.getValue();
   }
}
