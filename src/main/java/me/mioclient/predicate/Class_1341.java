package me.mioclient.predicate;

import java.util.function.Predicate;
import me.mioclient.module.render.SearchModule;

public class Class_1341 implements Predicate {
   public SearchModule field_4333;

   public Class_1341(SearchModule var1) {
      super();
      this.field_4333 = var1;
   }

   @Override
   public boolean test(Object var1) {
      return this.field_4333.field_2081.getValue();
   }
}
