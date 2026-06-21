package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.WaypointsModule;

public class Class_1369 implements Predicate {
   public WaypointsModule field_4452;

   public Class_1369(WaypointsModule var1) {
      super();
      this.field_4452 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4452.field_3310.getValue();
   }
}
