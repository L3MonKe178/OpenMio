package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.SearchModule;

public class Class_0497 implements Predicate {
   public SearchModule field_1565;

   public Class_0497(SearchModule var1) {
      super();
      this.field_1565 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_1565.field_2084.getValue();
   }
}
